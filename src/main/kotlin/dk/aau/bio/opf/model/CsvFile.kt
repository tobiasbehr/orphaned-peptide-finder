package dk.aau.bio.opf.model

/**
 * @author Tobias Behr
 */
class CsvFile(val header: List<HeaderColumn>, val content: List<Array<String>>) {
    data class HeaderColumn(val name: String, val index: Int) {
        override fun toString(): String = name
    }
}