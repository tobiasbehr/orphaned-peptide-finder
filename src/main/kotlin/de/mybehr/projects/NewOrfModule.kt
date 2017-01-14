package de.mybehr.projects

import com.google.inject.AbstractModule
import de.mybehr.projects.service.CsvFileReader
import de.mybehr.projects.service.FileReader

/**
 * @author Tobias Behr
 */
class NewOrfModule : AbstractModule() {
    override fun configure() {
        bind(FileReader::class.java).to(CsvFileReader::class.java)
    }

}