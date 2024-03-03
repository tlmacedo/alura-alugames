package br.com.alura.alugames.dados

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object Banco {

    fun factoryConnection(): Connection? {
        return try {
            DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/alugames", "root", "MTlm@4879")
        } catch (ex: SQLException) {
            ex.printStackTrace()
            null
        }
    }

}