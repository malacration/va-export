package br.andrew.sovis.va_export

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class VaExportApplication

fun main(args: Array<String>) {
	runApplication<VaExportApplication>(*args)
}
