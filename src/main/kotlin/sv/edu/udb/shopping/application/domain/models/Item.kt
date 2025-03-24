package sv.edu.udb.shopping.application.domain.models

/**
 * Item - Clase que se encarga de representar un producto en stock.
 *
 * @author VV121782
 * @since v1.0
 */
data class Item (
    val id: Long ? = null,
    val nombre: String,
    val precio: Double,
    var unidades: Int
) {
    // Clase constructora
    init {
        require(nombre.isNotBlank()) { "El nombre del producto no deberia de estar vacio." }
        require(precio >= 0) { "El precio no del producto no deberia de ser negativo." }
        require(unidades >= 0) { "Las unidades del producto no deberia de ser negativas." }
    }
}