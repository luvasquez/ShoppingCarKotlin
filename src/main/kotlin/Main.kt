import sv.edu.udb.shopping.application.CarritoCompraService
import sv.edu.udb.shopping.application.CarritoCompraServiceImpl
import sv.edu.udb.shopping.application.ItemService
import sv.edu.udb.shopping.application.ItemServiceImpl
import sv.edu.udb.shopping.application.domain.models.Item

val iva = 0.13

fun main(args: Array<String>) {
    val itemService = ItemServiceImpl()
    val carritoCompraService = CarritoCompraServiceImpl()


    var opcion: Int
    itemService.cargarProductosDefaults()

    do {
        println("\n============================================================")
        println("🛒  MENÚ DE COMPRA - TIENDA VIRTUAL - DSM941 PRIMER PROYECTO")
        println("============================================================")
        println("1️⃣  Ver listado de productos")
        println("2️⃣  Agregar productos al carrito")
        println("3️⃣  Eliminar productos del carrito")
        println("4️⃣  Ver carrito de compras")
        println("5️⃣  Pagar carrito")
        println("0️⃣  Salir")
        println("=============================================================")
        print("➡️  Seleccione una opción: ")

        val input = readlnOrNull()
        opcion = input?.toIntOrNull() ?: -1

        when (opcion) {
            1 -> verListadoProductos(itemService.obtenerTodos())
            2 -> agregarProductos(carritoCompraService, itemService)
            3 -> eliminarProductos(carritoCompraService)
            4 -> verCarrito(carritoCompraService, itemService)
            5 -> pagarCarrito(carritoCompraService, itemService)
            0 -> println("\n👋 Gracias por su compra. ¡Vuelva pronto!")
            else -> println("\n❌ Opción no válida. Intente de nuevo.")
        }
    } while (opcion != 0)
}


fun verListadoProductos(productos: List<Item>) {
    println("\n=============================")
    println("🛍️  Listado de productos disponibles")
    println("=============================")

    if (productos.isEmpty()) {
        println("❌ No hay productos disponibles en la tienda.")
    } else {
        productos.forEach { item ->
            println("🔹 ${item.nombre}")
            println("   Precio: \$${item.precio.format()}")
            println("   Unidades disponibles: ${item.unidades}")
            println("   ID: ${item.id ?: "No asignado"}")
            println("=============================")
        }
    }

}


fun agregarProductos(carritoService : CarritoCompraService, itemService : ItemService) {
    println("\n=============================")
    println("🛍️  Agregar producto al carrito")
    println("=============================")

    println("\nIngrese el ID del producto que desea agregar al carrito: ")
    val id = readlnOrNull()?.toLongOrNull()
    if (id == null) {
        println("❌ ID inválido.")
        return
    }

    println("Ingrese la cantidad que desea comprar: ")
    val cantidad = readlnOrNull()?.toIntOrNull()
    if (cantidad == null || cantidad <= 0) {
        println("❌ Cantidad inválida.")
        return
    }
    val save = itemService.validarOrden(id, cantidad)
    if(save) {
        carritoService.agregarProducto(id, cantidad)
    }
}



fun eliminarProductos(carritoService : CarritoCompraService) {
    println("\n=============================")
    println("🛍️  Eliminar producto del carrito")
    println("=============================")

    println("\nIngrese el ID del producto que desea eliminar: ")
    val id = readlnOrNull()?.toLongOrNull()
    if (id == null) {
        println("❌ ID inválido.")
        return
    }

    println("Ingrese la cantidad que desea eliminar: ")
    val cantidad = readlnOrNull()?.toIntOrNull()
    if (cantidad == null || cantidad <= 0) {
        println("❌ Cantidad inválida.")
        return
    }

    carritoService.removerProducto(id, cantidad)
}

fun verCarrito(carritoService : CarritoCompraService, itemService : ItemService) {

    println("\n=============================")
    println("🛍️  Carrito de compras")
    println("=============================")

    var itemReservados = carritoService.obtenerReservas()
    var total = 0.0

    if (itemReservados.isEmpty()) {
        println("❌ No hay productos en el carrito.")
    } else {
        itemReservados.forEach { item ->

            var itemRegistrado = itemService.obtenerItemPorId(item.id)

            if(itemRegistrado != null) {

                var subtotal = item.cantidad * itemRegistrado.precio

                println("🔹 ${itemRegistrado.nombre}")
                println("   Precio unitario: \$${itemRegistrado.precio.format()}")
                println("   Unidades: ${item.cantidad}")
                println("   subtotal: \$${subtotal.format()}")

                total += subtotal
                println("=============================")
            }
        }

        println("   Total: \$${total.format()}")
    }

}

fun pagarCarrito(carritoService : CarritoCompraService, itemService : ItemService) {

    println("\n=============================")
    println("🛍️  Pagar carrito")
    println("=============================")

    println("\n¿Desea pagar los productos de su carrito? (S/N)")
    val op = readlnOrNull()
    if (op == null) {
        println("❌ Opción invalida.")
        return
    } else if(op.lowercase() == "n" ) {
        return
    }

    println("\n=============================")
    println("🛍️  Imprimiendo factura")
    println("=============================")

    var itemReservados = carritoService.obtenerReservas()
    var total = 0.0

    if (itemReservados.isEmpty()) {
        println("❌ No hay productos en el carrito.")
    } else {
        itemReservados.forEach { item ->

            var itemRegistrado = itemService.obtenerItemPorId(item.id)

            if(itemRegistrado != null) {

                var subtotal = item.cantidad * itemRegistrado.precio
                // En esta sección actualizamos el stock
                itemRegistrado.unidades =  itemRegistrado.unidades - item.cantidad;
                itemService.actualizarItem(item.id, itemRegistrado)

                println("🔹 ${itemRegistrado.nombre}")
                println("   Precio unitario: \$${itemRegistrado.precio.format()}")
                println("   Unidades: ${item.cantidad}")
                println("   subtotal: \$${subtotal.format()}")

                total += subtotal
                println("=============================")
            }
        }

        var subTotalIva = iva * total
        total += subTotalIva

        println("   IVA (13%): \$${subTotalIva.format()}")
        println("   Total: \$${total.format()}")

        println("=============================")
        println("   ¡Muchas gracias por su compra!")

        println("\n\n Puede continuar haciendo más compras...")

        carritoService.limpiarCarrito()
    }
}


fun Double.format(): String {
    return String.format("%.2f", this) // Formatea el precio a dos decimales
}
