package dk.aau.bio.opf.service.file

import dk.aau.bio.opf.model.FileFormat
import java.io.File

/**
 * @author Tobias Behr
 */
interface FileHandler<T> {
    fun read(file : File, format : FileFormat) : T

    fun write(content: T, format : FileFormat, targetFolder: File, filename: String)
}