function initCategoria(){

    const tablaBody   = document.querySelector("#view-categorias tbody");
    const form        = document.querySelector("#form-categoria");
    const inputBuscar = document.querySelector("#inputBuscar");

    const modal = document.getElementById("modalEditarCategoria");
    const btnActualizar = document.getElementById("btnActualizar");

    // campos modal
    const editId = document.getElementById("edit-id");
    const editNombre = document.getElementById("edit-nombre");
    const editDescripcion = document.getElementById("edit-descripcion");
    const editActivo = document.getElementById("edit-activo");


    if(!tablaBody) return;


    async function listar(){
        const r = await fetch("/categoria/api/listar");
        const data = await r.json();

        tablaBody.innerHTML = "";

        data.forEach(c=>{
            tablaBody.innerHTML += `
            <tr>
                <td>${c.id}</td>
                <td>${c.nombre}</td>
                <td>${c.descripcion ?? ''}</td>
                <td>
                    <span class="badge ${c.activo?'bg-success':'bg-secondary'}">
                        ${c.activo?'Activo':'Inactivo'}
                    </span>
                </td>
                <td>
                    <button onclick="editar(${c.id})" class="btn btn-sm btn-outline-primary">
                        <i class="fa-solid fa-edit"></i>
                    </button>
                    <button onclick="eliminar(${c.id})" class="btn btn-sm btn-outline-danger">
                        <i class="fa-solid fa-trash"></i>
                    </button>
                </td>
            </tr>
            `;
        })
    }


    form.addEventListener("submit",async(e)=>{
        e.preventDefault();

        const obj={
            nombre: form.nombre.value,
            descripcion: form.descripcion.value,
            activo: form.activo.value==="1"
        };

        await fetch("/categoria/api/registrar",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(obj)
        });

        form.reset();
        listar();
    });


    window.eliminar = async(id)=>{
        if(confirm("Eliminar?")){
            await fetch(`/categoria/api/eliminar/${id}`,{
                method:"DELETE"
            });
            listar();
        }
    }


    window.editar = async(id)=>{

        const r = await fetch("/categoria/api/listar");
        const lista = await r.json();

        const cat = lista.find(x=> x.id==id);

        editId.value = cat.id;
        editNombre.value = cat.nombre;
        editDescripcion.value = cat.descripcion;
        editActivo.value = cat.activo?'1':'0';

        new bootstrap.Modal(modal).show();
    }


    btnActualizar.addEventListener("click",async()=>{

        const id = editId.value;

        const obj = {
            nombre: editNombre.value,
            descripcion: editDescripcion.value,
            activo: editActivo.value==="1"
        };

        await fetch(`/categoria/api/editar/${id}`,{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify(obj)
        });

        bootstrap.Modal.getInstance(modal).hide();
        listar();
    })


    inputBuscar.addEventListener("keyup", async (e) => {

        const texto = e.target.value.trim();

        // si está vacío vuelve a listar normal
        if(texto === ""){
            listar();
            return;
        }

        const r = await fetch(`/categoria/api/buscar?texto=${texto}`);
        const data = await r.json();

        tablaBody.innerHTML = "";

        data.forEach(c=>{
            tablaBody.innerHTML += `
        <tr>
            <td>${c.id}</td>
            <td>${c.nombre}</td>
            <td>${c.descripcion ?? ""}</td>
            <td>
                <span class="badge ${c.activo ? 'bg-success':'bg-secondary'}">
                    ${c.activo?'Activo':'Inactivo'}
                </span>
            </td>
            <td>
                <button class="btn btn-sm btn-outline-primary" onclick="editar(${c.id})">
                    <i class="fa-solid fa-edit"></i>
                </button>
                <button class="btn btn-sm btn-outline-danger" onclick="eliminar(${c.id})">
                    <i class="fa-solid fa-trash"></i>
                </button>
            </td>
        </tr>
        `;
        })
    })



    listar();
}

document.addEventListener("DOMContentLoaded",initCategoria);


