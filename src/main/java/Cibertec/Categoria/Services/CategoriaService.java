package Cibertec.Categoria.Services;

import Cibertec.Categoria.Model.Categoria;
import Cibertec.Categoria.Repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    // Listar solo activas (activo = 1) ( USO )
    public List<Categoria> listarActivas() {
        return categoriaRepository.findByActivoTrue();
    }

    // Buscar una categoría por ID ( USO )
    public Categoria buscarPorId(Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);
    }

    /** 🔹 Guardar o actualizar categoría */
    public Categoria guardar(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    /** 🔹 Listar todas (activas e inactivas) */
    public List<Categoria> listarTodas() {
        return categoriaRepository.findAll();
    }

    /** 🔹 Cambiar estado (activar/desactivar) */
    public void cambiarEstado(Long id, boolean activo) {
        Categoria cat = buscarPorId(id);
        if (cat != null) {
            cat.setActivo(activo);
            categoriaRepository.save(cat);
        }
    }
}
