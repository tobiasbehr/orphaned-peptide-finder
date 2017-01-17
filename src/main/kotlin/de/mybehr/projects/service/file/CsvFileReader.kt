package de.mybehr.projects.service.file

import au.com.bytecode.opencsv.CSVReader
import de.mybehr.projects.model.CsvFile
import de.mybehr.projects.model.FileFormat
import java.io.File

/**
 * @author Tobias Behr
 */
class CsvFileReader : FileReader<CsvFile> {

    override fun read(file: File, format: FileFormat): CsvFile {
        val csvReader = CSVReader(java.io.FileReader(file), format.delimiter)
        val csvFile = csvReader.readAll()
        csvReader.close()

        return CsvFile(createHeader(csvFile.get(0)), csvFile.subList(1, csvFile.size))
    }

    fun createHeader(input: Array<String>) : List<CsvFile.HeaderColumn> {
        val header = mutableListOf<CsvFile.HeaderColumn>()
        input.forEachIndexed { i, s ->  header.add(CsvFile.HeaderColumn(s, i))}
        return header
    }

}