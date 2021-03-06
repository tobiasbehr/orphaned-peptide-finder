package dk.aau.bio.opf.model

/**
 * @author Tobias Behr
 */
class FastAFile(val filename: String, val entries : List<FastAEntry>) {

    data class FastAEntry(val header: String, val sequence: String)
}

