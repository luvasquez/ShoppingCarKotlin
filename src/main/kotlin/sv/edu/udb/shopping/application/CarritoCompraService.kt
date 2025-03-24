package sv.edu.udb.shopping.application

import sv.edu.udb.shopping.application.domain.models.ItemReservado

interface CarritoCompraService {
    fun agregarProducto(id : Long, cantidad : Int)
    fun removerProducto(id: Long, cantidad: Int)
    fun obtenerReservas() : List<ItemReservado>
    fun limpiarCarrito()
}