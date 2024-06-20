package br.andrew.sovis.va_export

import br.andrew.sovis.va_export.model.sap.RetornoSap
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.junit.jupiter.api.Assertions
import org.springframework.scheduling.annotation.EnableScheduling
import kotlin.test.Test


@EnableScheduling
class RetornoSapTests {

    @Test
    fun teste(){
        val json = "{\"CardCode\":\"CLI0001934\",\"DocDueDate\":\"2024-06-19\",\"DocumentLines\":[{\"ItemCode\":\"PAC0000229\",\"Quantity\":\"6.0\",\"UnitPrice\":\"16.0\",\"Usage\":9,\"DiscountPercent\":0.0,\"AccountCode\":\"3.1.1.002.00003\",\"ItemDescription\":\"SUSTEN POSTURA 17 5KG\",\"CommisionPercent\":12.0,\"LineNum\":0,\"U_preco_base\":16.0,\"U_preco_negociado\":16.0,\"WarehouseCode\":\"500.01\",\"CostingCode\":\"500\",\"CostingCode2\":\"50000201\",\"MeasureUnit\":\"SACA 5KG\"},{\"ItemCode\":\"PAC0000224\",\"Quantity\":\"4.0\",\"UnitPrice\":\"17.0\",\"Usage\":9,\"DiscountPercent\":0.0,\"AccountCode\":\"3.1.1.002.00003\",\"ItemDescription\":\"SUSTEN CODORNA POSTURA 20 5KG\",\"CommisionPercent\":12.0,\"LineNum\":1,\"U_preco_base\":17.0,\"U_preco_negociado\":17.0,\"WarehouseCode\":\"500.01\",\"CostingCode\":\"500\",\"CostingCode2\":\"50000201\",\"MeasureUnit\":\"SACA 5KG\"},{\"ItemCode\":\"PAC0000217\",\"Quantity\":\"1.0\",\"UnitPrice\":\"138.0\",\"Usage\":9,\"DiscountPercent\":0.0,\"AccountCode\":\"3.1.1.002.00003\",\"ItemDescription\":\"SUSTEN POSTURA CONCENTRADO 30% 40KG\",\"CommisionPercent\":12.0,\"LineNum\":2,\"U_preco_base\":138.0,\"U_preco_negociado\":138.0,\"WarehouseCode\":\"500.01\",\"CostingCode\":\"500\",\"CostingCode2\":\"50000201\",\"MeasureUnit\":\"SACA 40KG\"},{\"ItemCode\":\"PAC0000213\",\"Quantity\":\"10.0\",\"UnitPrice\":\"112.0\",\"Usage\":9,\"DiscountPercent\":0.0,\"AccountCode\":\"3.1.1.002.00003\",\"ItemDescription\":\"SUSTEN FRANGO INICIAL 40KG\",\"CommisionPercent\":12.0,\"LineNum\":3,\"U_preco_base\":112.0,\"U_preco_negociado\":112.0,\"WarehouseCode\":\"500.01\",\"CostingCode\":\"500\",\"CostingCode2\":\"50000201\",\"MeasureUnit\":\"SACA 40KG\"}],\"BPL_IDAssignedToInvoice\":\"2\",\"DocDate\":\"2024-06-19\",\"SalesPersonCode\":100,\"PaymentGroupCode\":\"33\",\"DocEntry\":9289,\"DocNum\":\"3333\",\"PaymentMethod\":\"pix-vencimento\",\"JournalMemo\":\"Sales Quotations - CLI0001934\",\"U_pedido_update\":\"1\",\"DiscountPercent\":0.0,\"TotalDiscount\":\"0.0\",\"SequenceModel\":\"0\",\"CardName\":\"ADAIR SCHONS\",\"ControlAccount\":\"1.1.2.001.00001\",\"DocType\":\"dDocument_Items\",\"DocObjectCode\":\"oQuotations\",\"ShipToCode\":\"ENTREGA\",\"Address\":\"RUARAMAL GRANADA,2500\\rKM 2500\\r69945000-ACRELANDIA-AC\\rBRASIL\",\"Cancelled\":\"tNO\",\"DocTotal\":\"1422.0\",\"DocumentStatus\":\"bost_Open\",\"U_assinatura\":\"0\",\"U_id_pedido_forca\":\"6\"}"
        val mapper = ObjectMapper().registerModule(KotlinModule())
        val saida = mapper.readValue(json, jacksonTypeRef<RetornoSap>())
        Assertions.assertEquals("3333",saida.DocNum)
    }
}