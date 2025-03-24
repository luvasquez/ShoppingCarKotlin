package sv.edu.udb.shopping.application

import sv.edu.udb.shopping.application.domain.models.Item
import java.util.concurrent.atomic.AtomicLong

/**
 * Implementación del servicio de ítems usando almacenamiento en memoria.
 */
class ItemServiceImpl : ItemService {
    private val idGenerator = AtomicLong(1) // Generador de IDs secuenciales
    private val items = mutableListOf<Item>()

    // Método para cargar productos predeterminados
    fun cargarProductosDefaults() {
        val productosDefaults = listOf(
            Item(id = idGenerator.getAndIncrement(), nombre = "Laptop", precio = 1200.0, unidades = 5),
            Item(id = idGenerator.getAndIncrement(), nombre = "Mouse", precio = 25.99, unidades = 50),
            Item(id = idGenerator.getAndIncrement(), nombre = "Teclado", precio = 75.49, unidades = 30),
            Item(id = idGenerator.getAndIncrement(), nombre = "Monitor", precio = 300.0, unidades = 10),
            Item(id = idGenerator.getAndIncrement(), nombre = "Cámara Web", precio = 50.0, unidades = 20)
        )

        items.addAll(productosDefaults)
        println("✅ Productos predeterminados cargados correctamente.")
    }

    override fun crearItem(item: Item): Item {
        val itemWithId = item.copy(id = idGenerator.getAndIncrement())
        items.add(itemWithId)
        return item
    }

    override fun obtenerItem(nombre: String): Item? {
        return items.find { it.nombre.lowercase() == nombre.lowercase() }
    }

    override fun obtenerItemPorId(id: Long): Item? {
        return items.find { it.id == id }
    }

    override fun obtenerTodos(): List<Item> {
        return items.toList() // Devuelve una copia inmutable de la lista
    }

    override fun actualizarItem(id: Long, itemActualizado: Item): Item? {
        val index = items.indexOfFirst { it.id == id }
        return if (index != -1) {
            items[index] = itemActualizado
            itemActualizado
        } else {
            null
        }
    }

    override fun eliminarItem(id: Long): Boolean {
        return items.removeIf { it.id == id }
    }

    override fun validarOrden(id: Long, cantidad: Int): Boolean {
        // Validar que el ID del producto exista
        val item = obtenerItemPorId(id)

        if (item == null) {
            println("❌ El producto con ID $id no existe.")
            return false
        }

        // Validar que la cantidad no sea mayor al stock disponible
        if (cantidad <= 0) {
            println("❌ La cantidad debe ser mayor a 0.")
            return false
        }
        if (item.unidades < cantidad) {
            println("❌ No hay suficiente stock de ${item.nombre}. Solo quedan ${item.unidades} unidades.")
            return false
        }
        return true
    }
}