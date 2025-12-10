function initProducto() {

    const tbody = document.querySelector("#tbody-productos");
    const form = document.querySelector("#form-producto");
    const inputBuscar = document.querySelector("#inputBuscarProducto");
    const filtroCategoria = document.querySelector("#filtroCategoria");

    if(!tbody) return;

    // ===================== LISTAR ===========================
    async function listar(){

        const txt = inputBuscar.value.trim();

        let url = txt ?
            `/producto/api/buscar?texto=${txt}`
            :
            "/producto/api/listar";

        const r = await fetch(url);
        const data = await r.json();

        renderTable(data);
    }

    // ===================== CARGAR LINEAS =====================
    async function cargarLineas(){

        const r = await fetch("/lineadiseno/api/listar");
        const data = await r.json();

        // combo del formulario principal
        const selLinea = form.linea;
        selLinea.innerHTML = `<option value="">Seleccionar...</option>`;

        data.forEach(l=>{
            selLinea.innerHTML += `<option value="${l.id}">${l.nombre}</option>`;
        });

        // combo para el modal editar
        const selEdit = document.querySelector("#edit-linea");
        if(selEdit){
            selEdit.innerHTML = `<option value="">Sin línea</option>`;
            data.forEach(l=>{
                selEdit.innerHTML += `<option value="${l.id}">${l.nombre}</option>`;
            });
        }
    }


    // ===================== RENDER TABLA =====================
    function renderTable(lista){
        tbody.innerHTML = "";

        lista.forEach(p => {

            tbody.innerHTML += `
            <tr>
                <td>${p.id}</td>
                <td>${p.nombre}</td>
                <td>${p.categoria?.nombre ?? "-"}</td>
                <td>${p.lineaDiseno?.nombre ?? "-"}</td>
                <td>${ p.precio ?? "A cotizar" }</td>
                <td>
                    <span class="badge ${p.activo?'bg-success':'bg-secondary'}">
                        ${p.activo?'Activo':'Inactivo'}
                    </span>
                </td>
                <td>
                    <button onclick="editarProducto(${p.id})"
                        class="btn btn-sm btn-primary">
                        <i class="fa-solid fa-edit"></i>
                    </button>

                    <button onclick="eliminarProducto(${p.id})"
                        class="btn btn-sm btn-danger">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </td>
            </tr>
            `;
        });
    }


    // ===================== REGISTRAR =====================
    form.addEventListener("submit", async(e)=>{

        e.preventDefault();

        const obj = {
            nombre: form.nombre.value,
            precio: form.precio.value,
            largo: form.largo.value,
            ancho: form.ancho.value,
            altura: form.alto.value, // Asegúrate que tu modelo use 'altura'
            peso: form.peso.value,
            material: form.material.value,
            descripcionCard: form.descripcionCard.value,
            descripcionCorta: form.descripcionCorta.value,
            descripcionLarga: form.descripcionLarga.value,
            imagen1: form.imagen1.value,
            imagen2: form.imagen2.value,
            imagen3: form.imagen3.value,
            activo: form.activo.value=="1",

            categoria: { id : form.categoria.value },
            lineaDiseno: form.linea.value? { id: form.linea.value } : null
        };

        await fetch(`/producto/api/registrar`,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(obj)
        });

        form.reset();
        listar();
    });


    // ===================== CARGAR CATEGORIAS =====================
    async function cargarCategorias(){

        const r = await fetch("/categoria/api/listar");
        const data = await r.json();

        filtroCategoria.innerHTML = `<option value="">Todas las categorías</option>`;

        const selForm = form.categoria;
        selForm.innerHTML = `<option value="">Seleccionar...</option>`;

        // Combo para el modal editar
        const selEdit = document.querySelector("#edit-categoria");
        if(selEdit){
            selEdit.innerHTML = `<option value="">Seleccionar...</option>`;
        }


        data.forEach(c=>{
            filtroCategoria.innerHTML += `<option value="${c.id}">${c.nombre}</option>`;
            selForm.innerHTML += `<option value="${c.id}">${c.nombre}</option>`;

            // Rellenar combo de edición
            if(selEdit){
                selEdit.innerHTML += `<option value="${c.id}">${c.nombre}</option>`;
            }
        });
    }


    // ===================== BUSCAR =====================
    inputBuscar.addEventListener("keyup", ()=> listar());


    // ===================== FILTRAR POR CATEGORIA =====================
    filtroCategoria.addEventListener("change",async()=>{

        let id = filtroCategoria.value;

        if(id === ""){
            listar();
            return;
        }

        const r = await fetch(`/producto/api/buscar-categoria?idCategoria=${id}`);
        const data = await r.json();

        renderTable(data);
    });


    // ===================== ELIMINAR =====================
    window.eliminarProducto = async(id)=>{
        if(confirm("¿Eliminar producto?")){
            await fetch(`/producto/api/eliminar/${id}`,{
                method:"DELETE"
            });
            listar();
        }
    };

    // ===================== BUSCAR POR ID PARA EDICION (ABRIR MODAL) =====================
    window.editarProducto = async (id) => {

        // 1. Obtener los datos del producto
        const r = await fetch(`/producto/api/buscar/${id}`);
        if (!r.ok) {
            console.error(`Error al buscar producto con ID ${id}: ${r.statusText}`);
            alert(`Error: No se encontró el producto con ID ${id}.`);
            return;
        }
        const p = await r.json();

        // 2. Rellenar los campos del modal de edición
        document.querySelector("#edit-id").value = p.id;
        document.querySelector("#edit-nombre").value = p.nombre ?? "";
        document.querySelector("#edit-precio").value = p.precio ?? "";

        // ** MAPEANDO DIMENSIONES Y PESO **
        document.querySelector("#edit-largo").value = p.largo ?? "";
        document.querySelector("#edit-ancho").value = p.ancho ?? "";
        document.querySelector("#edit-alto").value = p.altura ?? "";
        document.querySelector("#edit-peso").value = p.peso ?? "";

        document.querySelector("#edit-material").value = p.material ?? "";
        document.querySelector("#edit-descripcionCard").value = p.descripcionCard ?? "";
        document.querySelector("#edit-descripcionCorta").value = p.descripcionCorta ?? "";
        document.querySelector("#edit-descripcionLarga").value = p.descripcionLarga ?? "";
        document.querySelector("#edit-imagen1").value = p.imagen1 ?? "";
        document.querySelector("#edit-imagen2").value = p.imagen2 ?? "";
        document.querySelector("#edit-imagen3").value = p.imagen3 ?? "";
        document.querySelector("#edit-activo").value = p.activo ? "1" : "0";

        // ** RELACIONES: CATEGORÍA Y LÍNEA DE DISEÑO **
        document.querySelector("#edit-categoria").value = p.categoria?.id ?? "";
        document.querySelector("#edit-linea").value = p.lineaDiseno?.id ?? "";

        // Abrir el modal
        const modal = new bootstrap.Modal(document.getElementById('modalEditarProducto'));
        modal.show();
    };


    // ===================== ACTUALIZAR =====================
    const btnActualizarProducto = document.querySelector("#btnActualizarProducto");

    if(btnActualizarProducto){
        btnActualizarProducto.addEventListener("click", async () => {

            const editId = document.querySelector("#edit-id").value;
            const editLineaId = document.querySelector("#edit-linea").value;
            const editCategoriaId = document.querySelector("#edit-categoria").value;

            const obj = {
                nombre: document.querySelector("#edit-nombre").value,
                precio: document.querySelector("#edit-precio").value,
                material: document.querySelector("#edit-material").value,
                descripcionCard: document.querySelector("#edit-descripcionCard").value,
                descripcionCorta: document.querySelector("#edit-descripcionCorta").value,
                descripcionLarga: document.querySelector("#edit-descripcionLarga").value,
                imagen1: document.querySelector("#edit-imagen1").value,
                imagen2: document.querySelector("#edit-imagen2").value,
                imagen3: document.querySelector("#edit-imagen3").value,
                activo: document.querySelector("#edit-activo").value == "1",

                // Incluyendo dimensiones en la actualización
                largo: document.querySelector("#edit-largo").value,
                ancho: document.querySelector("#edit-ancho").value,
                altura: document.querySelector("#edit-alto").value,
                peso: document.querySelector("#edit-peso").value,

                categoria: editCategoriaId ? { id : editCategoriaId } : null,
                lineaDiseno: editLineaId ? { id: editLineaId } : null
            };


            await fetch(`/producto/api/editar/${editId}`,{
                method:"PUT",
                headers:{"Content-Type":"application/json"},
                body:JSON.stringify(obj)
            });

            // Ocultar el modal y actualizar la tabla
            const modal = bootstrap.Modal.getInstance(document.getElementById('modalEditarProducto'));
            modal.hide();

            listar();
        });
    }

    // ===================== INICIAL =====================
    cargarCategorias();
    cargarLineas();
    listar();

} // Cierre de initProducto()

document.addEventListener("DOMContentLoaded", initProducto);
// La llave final extra ha sido eliminada.