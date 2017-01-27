package de.mybehr.projects.service.file

import au.com.bytecode.opencsv.CSVReader
import au.com.bytecode.opencsv.CSVWriter
import de.mybehr.projects.model.CsvFile
import de.mybehr.projects.model.FileFormat
import java.io.File
import java.io.FileWriter

/**
 * @author Tobias Behr
 */
class CsvFileHandler : FileHandler<CsvFile> {
    override fun write(content: CsvFile, format: FileFormat, file: File) {
        val writer = CSVWriter(FileWriter(file.resolve("kotlin_output.txt")), format.delimiter, CSVWriter.NO_QUOTE_CHARACTER)
        writer.writeNext(content.header.map(CsvFile.HeaderColumn::name).toTypedArray())
        writer.writeAll(content.content)
        writer.close()
    }

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