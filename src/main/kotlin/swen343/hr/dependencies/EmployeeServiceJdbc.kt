package swen343.hr.dependencies

import com.google.inject.Singleton
import swen343.hr.models.Employee
import swen343.hr.datasource.MySqlDatabase
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement

@Singleton
class EmployeeServiceJdbc : EmployeeService {

    override fun getEmployee(username: String): Employee? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEmployees(): List<Employee> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateEmployee(employee: Employee) {

        var stmt: Statement? = null
        var resultset: ResultSet? = null

        try {
            stmt = MySqlDatabase.conn!!.createStatement()
            resultset = stmt!!.executeQuery("INSERT INTO employees(id,firstName,lastName,title,department,salary,phoneNumber,email,address) VALUE(?,?,?,?,?,?,?,?,?);")

            if (stmt.execute("INSERT INTO employees(id,firstName,lastName,title,department,salary,phoneNumber,email,address) VALUE(?,?,?,?,?,?,?,?,?);")) {
                resultset = stmt.resultSet //remove if borked.
            }

            while (resultset!!.next()) {
                println(resultset.getString("Database"))
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close()
                } catch (sqlEx: SQLException) {
                }

                resultset = null
            }

            if (stmt != null) {
                try {
                    stmt.close()
                } catch (sqlEx: SQLException) {
                }

                stmt = null
            }

            if (MySqlDatabase.conn != null) {
                try {
                    MySqlDatabase.conn!!.close()
                } catch (sqlEx: SQLException) {
                }

                MySqlDatabase.conn = null
            }
        }

        TODO()
    }

    fun editEmployee(employee: Employee) {

        var stmt: Statement? = null
        var resultset: ResultSet? = null

        try {
            stmt = MySqlDatabase.conn!!.createStatement()
            resultset = stmt!!.executeQuery("UPDATE employees SET id =employee.id, firstName = ?, lastName = ?, title = ?, department = ?, salary = ?, phoneNumber = ?, email = ?, address = ? ")

            if (stmt.execute("UPDATE employees SET id =employee.id, firstName = ?, lastName = ?, title = ?, department = ?, salary = ?, phoneNumber = ?, email = ?, address = ?")) {
                resultset = stmt.resultSet //remove if borked.
            }

            while (resultset!!.next()) {
                println(resultset.getString("Database"))
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close()
                } catch (sqlEx: SQLException) {
                }

                resultset = null
            }

            if (stmt != null) {
                try {
                    stmt.close()
                } catch (sqlEx: SQLException) {
                }

                stmt = null
            }

            if (MySqlDatabase.conn != null) {
                try {
                    MySqlDatabase.conn!!.close()
                } catch (sqlEx: SQLException) {
                }

                MySqlDatabase.conn = null
            }
        }
    }

    override fun deleteEmployee(username: String) {
        var stmt: Statement? = null
        var resultset: ResultSet? = null

        try {
            stmt = MySqlDatabase.conn!!.createStatement()
            resultset = stmt!!.executeQuery("DELETE FROM employee [WHERE id=employee.id]")

            if (stmt.execute("DELETE FROM employee [WHERE id=employee.id]")) {
                resultset = stmt.resultSet //remove if borked.
            }

            while (resultset!!.next()) {
                println(resultset.getString("Database"))
            }
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } finally {
            // release resources
            if (resultset != null) {
                try {
                    resultset.close()
                } catch (sqlEx: SQLException) {
                }

                resultset = null
            }

            if (stmt != null) {
                try {
                    stmt.close()
                } catch (sqlEx: SQLException) {
                }

                stmt = null
            }

            if (MySqlDatabase.conn != null) {
                try {
                    MySqlDatabase.conn!!.close()
                } catch (sqlEx: SQLException) {
                }

                MySqlDatabase.conn = null
            }
        }
    }
}

