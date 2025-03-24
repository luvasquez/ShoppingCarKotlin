package sv.edu.udb.shopping.application

import sv.edu.udb.shopping.application.domain.models.ItemReservado

class CarritoCompraServiceImpl : CarritoCompraService {
    private val itemsReservados  = mutableListOf<ItemReservado>()

    /**
     * Agrega un item a la reserva. Si el item ya existe, suma la cantidad.
     */
    override fun agregarProducto(id: Long, cantidad: Int) {
        if (cantidad <= 0) {
            println("La cantidad debe ser mayor a 0.")
            return
        }

        val itemExistente = itemsReservados.find { it.id == id }
        if (itemExistente != null) {
            itemExistente.cantidad += cantidad
        } else {
            itemsReservados.add(ItemReservado(id, cantidad))
        }
        println("Se ha agregado la cantidad de $cantidad unidades del producto con ID: $id al carrito de compras.")
    }

    /**
     * Remueve una cantidad específica de un item de la reserva.
     * Si la cantidad a remover es mayor a la disponible, elimina el item por completo.
     */
    override fun removerProducto(id: Long, cantidad: Int) {
        if (cantidad <= 0) {
            println("La cantidad debe ser mayor a 0.")
            return
        }

        val itemExistente = itemsReservados.find { it.id == id }
        if (itemExistente != null) {
            if (cantidad >= itemExistente.cantidad) {
                itemsReservados.remove(itemExistente)
                println("Se eliminó el item $id del carrito.")
            } else {
                itemExistente.cantidad -= cantidad
                println("Se quitaron: $cantidad unidades del item $id del carrito.")
            }
        } else {
            println("El item con id no. $id no se encuentra en el carrito de compras.")
        }
    }

    override fun obtenerReservas(): List<ItemReservado> {
        return itemsReservados.toList()
    }

    override fun limpiarCarrito() {
        itemsReservados.clear()
        println("El carrito ha sido vaciado.")
    }
}