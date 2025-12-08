package Cibertec.Empleado.Repository;

import Cibertec.Empleado.Model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

    Optional<Empleado> findByDni(String dni);

    boolean existsByDni(String dni);

    boolean existsByCorreo(String correo);

    boolean existsByCodigoTrabajador(String codigoTrabajador);
}
