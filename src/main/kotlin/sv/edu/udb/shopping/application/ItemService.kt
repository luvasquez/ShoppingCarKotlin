package sv.edu.udb.shopping.application

import sv.edu.udb.shopping.application.domain.models.Item

/**
 * Interfaz que define las operaciones del servicio para gestionar los ítems.
 */
interface ItemService {
    fun crearItem(item: Item): Item
    fun obtenerItem(nombre: String): Item?
    fun obtenerItemPorId(id: Long): Item?
    fun obtenerTodos(): List<Item>
    fun actualizarItem(id: Long, itemActualizado: Item): Item?
    fun eliminarItem(id: Long): Boolean
    fun validarOrden(id: Long, cantidad : Int) : Boolean
}