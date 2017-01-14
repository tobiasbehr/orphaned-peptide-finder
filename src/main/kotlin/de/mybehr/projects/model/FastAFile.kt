package de.mybehr.projects.model

/**
 * @author Tobias Behr
 */
class FastAFile(filename: String, entries : List<FastAEntry>) {

    data class FastAEntry(val header: String, val sequence: String)
}

