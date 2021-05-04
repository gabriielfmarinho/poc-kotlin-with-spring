package com.poc.with.spring.config

import com.poc.with.spring.log.loggerFor
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestExecutionListener
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import java.util.*
import java.util.function.Consumer
import javax.sql.DataSource

class CleanMySqlDataBaseAfterTestExecution : TestExecutionListener {

    companion object {
        private val log = loggerFor(CleanMySqlDataBaseAfterTestExecution::class.java)
    }

    private lateinit var dataSource: DataSource

    @Throws(Exception::class)
    override fun afterTestExecution(testContext: TestContext) {
        dataSource = getDataSource(testContext)
        cleanDataBase()
    }

    @Throws(SQLException::class)
    private fun cleanDataBase() {
        log.info("init clean database")
        val connection = dataSource.connection
        val statement = connection.createStatement()
        try {
            connection.use {
                statement.use {
                    execute(statement, "SET REFERENTIAL_INTEGRITY FALSE")
                    val tableNames = executeQuery(statement, "SHOW TABLES")
                    getRows(tableNames)
                        ?.forEach(Consumer { tableName: String -> execute(statement, "TRUNCATE TABLE ${tableName}") })
                    val sequences = executeQuery(statement, "SHOW SEQUENCES")
                    getRows(sequences)
                        ?.forEach(Consumer { sequence: String ->
                            execute(statement, "ALTER SEQUENCE ${sequence} RESTART WITH 1")
                        })
                    execute(statement, "SET REFERENTIAL_INTEGRITY TRUE")
                }
            }
        } catch (e: Exception) {
            log.error("cannot be execute query", e)
        }
    }

    @Throws(SQLException::class)
    private fun executeQuery(statement: Statement, sql: String): ResultSet {
        return statement.executeQuery(sql)
    }

    private fun getRows(resultSet: ResultSet): Set<String>? {
        try {
            resultSet.use {
                val data = HashSet<String>()
                while (resultSet.next()) {
                    data.add(resultSet.getString(1))
                }
                return data
            }
        } catch (e: Exception) {
            log.error("cannot be extract rows", e)
            return null
        }
    }

    private fun getDataSource(testContext: TestContext) =
        testContext.applicationContext.getBean(DataSource::class.java)

    private fun execute(statement: Statement, sql: String) {
        try {
            statement.execute(sql)
        } catch (e: Exception) {
            log.error("cannot be execute query, ", e)
        }
    }
}
