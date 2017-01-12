package de.mybehr.projects

import javafx.util.StringConverter
import java.io.File

/**
 * @author Tobias Behr
 */
class FileToStringConverter(): StringConverter<File>() {
    override fun toString(file: File?): String? = file?.canonicalPath

    override fun fromString(string: String?): File? {
        throw UnsupportedOperationException()
    }

}