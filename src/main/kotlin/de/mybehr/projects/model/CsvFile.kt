package de.mybehr.projects.model

/**
 * @author Tobias Behr
 */
class CsvFile(val header: List<HeaderColumn>, val content: List<Array<String>>) {
    class HeaderColumn(val name: String, val index: Int) {
        override fun toString(): String = name
    }
}