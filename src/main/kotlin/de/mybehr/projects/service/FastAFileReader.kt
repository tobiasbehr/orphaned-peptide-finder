package de.mybehr.projects.service

import de.mybehr.projects.model.FastAFile
import de.mybehr.projects.model.FileFormat
import java.io.File

/**
 * @author Tobias Behr
 */
class FastAFileReader : FileReader<FastAFile> {

    override fun read(file: File, format: FileFormat): FastAFile {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}