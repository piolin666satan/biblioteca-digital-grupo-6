package com.grupo6.biblioteca_digital.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.grupo6.biblioteca_digital.exception.BadRequestException;
import com.grupo6.biblioteca_digital.exception.ResourceNotFoundException;
import com.grupo6.biblioteca_digital.model.dto.LibroDTO;
import com.grupo6.biblioteca_digital.model.entity.CategoriaEntity;
import com.grupo6.biblioteca_digital.model.entity.LibroEntity;
import com.grupo6.biblioteca_digital.repository.CategoriaRepository;
import com.grupo6.biblioteca_digital.repository.LibroRepository;

@Service
public class LibroServices {

    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;

    public LibroServices(
            LibroRepository libroRepository,
            CategoriaRepository categoriaRepository) {

        this.libroRepository = libroRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // =========================
    // LISTAR
    // =========================

    public List<LibroDTO> listarLibrosDTO() {

        return libroRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    // =========================
    // BUSCAR POR ID
    // =========================

    public Optional<LibroDTO> buscarPorId(Long id) {

        return libroRepository.findById(id)
                .map(this::toDTO);
    }

    // =========================
    // ELIMINAR
    // =========================

    public void eliminarLibro(Long id) {

        if (!libroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Libro no encontrado");
        }

        libroRepository.deleteById(id);
    }

    // =========================
    // CREAR LIBRO
    // =========================

    public LibroDTO guardarLibroDTO(LibroDTO libroDTO) {

        // ========= VALIDACIONES =========

        if (libroDTO.getTitulo() == null || libroDTO.getTitulo().isBlank()) {
            throw new BadRequestException("El título es obligatorio");
        }

        if (libroDTO.getCantidad() == null || libroDTO.getCantidad() <= 0) {
            throw new BadRequestException("La cantidad debe ser mayor a 0");
        }

        if (libroDTO.getCategoriaId() == null) {
            throw new BadRequestException("La categoría es obligatoria");
        }

        // ========= BUSCAR O CREAR CATEGORIA =========

        CategoriaEntity categoria = categoriaRepository
                .findById(libroDTO.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("La categoría con el ID " + libroDTO.getCategoriaId() + " no existe."));

        // ========= VALIDAR DUPLICADOS =========

        Optional<LibroEntity> existente =
                libroRepository.findByTitulo(libroDTO.getTitulo());

        if (existente.isPresent()) {

            LibroEntity libroExistente = existente.get();

            libroExistente.setCantidad(
                    libroExistente.getCantidad() + libroDTO.getCantidad());

            libroExistente.actualizarDisponibilidad();

            LibroEntity actualizado = libroRepository.save(libroExistente);

            return toDTO(actualizado);
        }

        // ========= CREAR NUEVO =========

        LibroEntity nuevoLibro = toEntity(libroDTO, categoria);

        nuevoLibro.actualizarDisponibilidad();

        LibroEntity guardado = libroRepository.save(nuevoLibro);

        return toDTO(guardado);
    }

    // =========================
    // ACTUALIZAR
    // =========================

    public LibroDTO actualizarLibro(Long id, LibroDTO libroDTO) {

        LibroEntity libro = libroRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Libro no encontrado"));

        libro.setTitulo(libroDTO.getTitulo());
        libro.setAutor(libroDTO.getAutor());
        libro.setCantidad(libroDTO.getCantidad());
        libro.setIsbn(libroDTO.getIsbn());
        libro.setPrecio(libroDTO.getPrecio());
        libro.setEditorial(libroDTO.getEditorial()); //agrego editorial, autor y isbn a la actualización, ya que se encuentran en el DTO pero no se estaban actualizando en la entidad, se agregan estas líneas para que se actualicen correctamente al momento de realizar una actualización de un libro existente

        // Buscar categoría
        CategoriaEntity categoria = categoriaRepository
                .findById(libroDTO.getCategoriaId())
                .orElseThrow(() -> new IllegalArgumentException("La categoría con el ID " + libroDTO.getCategoriaId() + " no existe."));

        libro.setCategoria(categoria);

        libro.actualizarDisponibilidad();

        LibroEntity actualizado = libroRepository.save(libro);

        return toDTO(actualizado);
    }

    // =========================
    // DTO -> ENTITY
    // =========================

    private LibroEntity toEntity(
            LibroDTO dto,
            CategoriaEntity categoria) {

        LibroEntity entity = new LibroEntity();

        entity.setTitulo(dto.getTitulo());

        entity.setAutor(dto.getAutor());

        entity.setCantidad(dto.getCantidad());

        entity.setIsbn(dto.getIsbn());

        entity.setPrecio(dto.getPrecio());

        entity.setEditorial(dto.getEditorial());

        entity.setCategoria(categoria);

        return entity;
    }

    // =========================
    // ENTITY -> DTO
    // =========================
private LibroDTO toDTO(LibroEntity entity) {

    LibroDTO dto = new LibroDTO();

    dto.setId(entity.getId());

    dto.setTitulo(entity.getTitulo());

    dto.setAutor(entity.getAutor());

    dto.setIsbn(entity.getIsbn());

    dto.setCantidad(entity.getCantidad());

    dto.setPrecio(entity.getPrecio());

    dto.setEstado(entity.getEstado());

    dto.setCategoriaId(entity.getCategoria().getId());

    dto.setEditorial(entity.getEditorial());

    return dto;
}
//errores encontrados, se agregan los campos autor, isbn y editorial al método toDTO para que se mapeen correctamente al momento de convertir una entidad a DTO, ya que estos campos se encuentran en la entidad pero no se estaban incluyendo en el DTO, se agregan estas líneas para que se muestren correctamente al listar o buscar libros por ID
}