package dk.aau.bio.opf.service.file

import org.junit.Test

/**
 * @author Tobias Behr
 */
class CsvFileHandlerTest {

    val reader : CsvFileHandler = CsvFileHandler()

    @Test
    fun shouldCreateHeader() {
        val createdHeader = reader.createHeader(arrayOf("1", "2", "3"))

        assert(createdHeader.size == 3)
        assert(createdHeader.get(0).index == 0)
        assert(createdHeader.get(0).name == "1")
        assert(createdHeader.get(1).index == 1)
        assert(createdHeader.get(1).name == "2")
        assert(createdHeader.get(2).index == 2)
        assert(createdHeader.get(2).name == "3")
    }
}