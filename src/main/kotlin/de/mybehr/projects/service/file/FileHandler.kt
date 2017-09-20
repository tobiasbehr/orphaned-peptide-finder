package de.mybehr.projects.service.file

import de.mybehr.projects.model.FileFormat
import java.io.File

/**
 * @author Tobias Behr
 */
interface FileHandler<T> {
    fun read(file : File, format : FileFormat) : T

    fun write(content: T, format : FileFormat, targetFolder: File, filename: String)
}