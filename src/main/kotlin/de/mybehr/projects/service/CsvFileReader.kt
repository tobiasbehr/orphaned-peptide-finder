package de.mybehr.projects.service

import au.com.bytecode.opencsv.CSVReader
import de.mybehr.projects.model.CsvFile
import java.io.File
import java.io.FileReader as JFileReader

/**
 * @author Tobias Behr
 */
class CsvFileReader : FileReader<CsvFile> {

    override fun read(file: File) : CsvFile {
        val csvReader = CSVReader(JFileReader(file), '\t')

        val csvFile = csvReader.readAll()

        return CsvFile(csvFile.get(0), csvFile.subList(1, csvFile.size))
    }

}