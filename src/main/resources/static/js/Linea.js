function initLinea(){

    const tbody = document.querySelector("#tbody-lineas");
    const form = document.querySelector("#form-linea");
    const inputBuscar = document.querySelector("#inputBuscarLinea");

    if(!tbody) return;


    // ================== LISTAR ====================
    async function listar(){
        const texto = inputBuscar.value;
        const url = texto? `/lineadiseno/api/buscar?texto=${texto}` : "/lineadiseno/api/listar";

        const r = await fetch(url);
        const data = await r.json();

        tbody.innerHTML="";
        data.forEach(l=>{

            tbody.innerHTML += `
            <tr>
                <td>${l.id}</td>
                <td>${l.nombre}</td>
                <td>${l.descripcion??""}</td>
                <td>
                    <span class="badge ${l.activo?'bg-success':'bg-secondary'}">
                        ${l.activo?'Activo':'Inactivo'}
                    </span>
                </td>
                <td>
                    <button onclick="editarLinea(${l.id})" class="btn btn-sm btn-outline-primary">
                        <i class="fa-solid fa-edit"></i>
                    </button>
                    <button onclick="eliminarLinea(${l.id})" class="btn btn-sm btn-outline-danger">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </td>
            </tr>
            `;
        })
    }


    // ================== REGISTRAR ====================
    form.addEventListener("submit",async(e)=>{
        e.preventDefault();

        const obj={
            nombre: form.nombre.value,
            descripcion: form.descripcion.value,
            activo: form.activo.value==="1"
        };

        await fetch("/lineadiseno/api/registrar",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(obj)
        });

        form.reset();
        listar();
    });


    // ================== ELIMINAR =====================
    window.eliminarLinea = async(id)=>{
        if(confirm("Eliminar?")){
            await fetch(`/lineadiseno/api/eliminar/${id}`,{
                method:"DELETE"
            });
            listar();
        }
    }


    // ================== MODAL EDITAR ====================
    const modal = document.getElementById("modalEditarLinea");
    const editId = document.getElementById("edit-id");
    const editNombre = document.getElementById("edit-nombre");
    const editDescripcion = document.getElementById("edit-descripcion");
    const editActivo = document.getElementById("edit-activo");
    const btnActualizar = document.getElementById("btnActualizarLinea");


    window.editarLinea = async(id)=>{

        const r = await fetch("/lineadiseno/api/listar");
        const lista = await r.json();

        const linea = lista.find(x=> x.id==id);

        editId.value = linea.id;
        editNombre.value = linea.nombre;
        editDescripcion.value = linea.descripcion;
        editActivo.value = linea.activo?'1':'0';

        new bootstrap.Modal(modal).show();
    }


    btnActualizar.addEventListener("click",async()=>{

        const id = editId.value;

        const obj = {
            nombre: editNombre.value,
            descripcion: editDescripcion.value,
            activo: editActivo.value==="1"
        };

        await fetch(`/lineadiseno/api/editar/${id}`,{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(obj)
        });

        bootstrap.Modal.getInstance(modal).hide();
        listar();
    })


    inputBuscar.addEventListener("keyup",()=>listar());


    listar();
}


document.addEventListener("DOMContentLoaded",initLinea);
