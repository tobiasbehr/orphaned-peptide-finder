package de.mybehr.projects.service.file

import de.mybehr.projects.model.FileFormat
import java.io.File

/**
 * @author Tobias Behr
 */
interface FileReader<T> {
    fun read(file : File, format : FileFormat) : T
}