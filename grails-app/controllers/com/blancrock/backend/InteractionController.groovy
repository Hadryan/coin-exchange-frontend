package com.blancrock.backend

/**
 * Created by Vladimir Havenchyk.
 */
class InteractionController {
    def queryService

    def getTickerInfo() {
        Map queryResult = queryService.getTickerInfo('XBTUSD')

        response.status = queryResult.status
        render queryResult.value
    }

    def getCurrencyPairs() {
        Map queryResult = queryService.getCurrencyPairs()

        response.status = queryResult.status
        render queryResult.value
    }

    def getRates() {
        Map queryResult = queryService.getRates()

        response.status = queryResult.status
        render queryResult.value
    }

    def queryOpenOrders() {
        Map queryResult = queryService.queryOpenOrders(true, '')

        response.status = queryResult.status
        render queryResult.value
    }

    def queryClosedOrders() {
        Map queryResult = queryService.queryClosedOrders(true, '', '', '', '', '')

        response.status = queryResult.status
        render queryResult.value
    }

    def getTradeHistory() {
        Map queryResult = queryService.getTradeHistory('', '')

        response.status = queryResult.status
        render queryResult.value
    }

    // Gets the details when a trade is clicked
    def getTradeDetails(){
        def orderParams = request.JSON
        def tradeId = orderParams.tradeId
        String response = queryService.getTradeDetails(tradeId)

        render response
    }

    def createNewOrder() {
        def orderParams = request.JSON
        def order = orderParams['order']

        String currencyPair = order['pair']
        String type = order['orderType']
        String side = order['type']
        BigDecimal volume = new BigDecimal(order['volume'] as String)
        BigDecimal price = 0
        if (type == 'Limit'){
            price = new BigDecimal(order['price'] as String)
        }

        Map queryResult = queryService.createOrder(currencyPair, type, side, volume, price)

        response.status = queryResult.status
        render queryResult.value
    }

    def newOrderSimpleBids() {
        Map queryResult = queryService.newOrderSimpleBids('XBTUSD')

        response.status = queryResult.status
        render queryResult.value
    }

    def newOrderSimpleAsks() {
        Map queryResult = queryService.newOrderSimpleAsks('XBTUSD')

        response.status = queryResult.status
        render queryResult.value
    }

    def cancelOrder() {
        def orderParams = request.JSON
        def orderId = orderParams['OrderId']
        
        Map queryResult = queryService.cancelOrder(orderId)

        response.status = queryResult.status
        render queryResult.value
    }

    def showOrderDetails() {
        def orderParams = request.JSON
        //@todo: need to use only style for all calls
        def orderId = orderParams['orderId']
        
        Map queryResult = queryService.showOrderDetails(orderId)

        response.status = queryResult.status
        render queryResult.value
    }

    // Gets teh trades when an order is clicked for order details
    def showTradeDetails() {
        def orderParams = request.JSON
        def orderId = orderParams['orderId']
        
        Map queryResult = queryService.showTradeDetails(orderId)

        response.status = queryResult.status
        render queryResult.value
    }

    def getRecentTrades() {
        Map queryResult = queryService.getRecentTrades('XBTUSD')

        response.status = queryResult.status
        render queryResult.value
    }

    def getBids() {
        Map queryResult = queryService.getBids('XBTUSD')

        response.status = queryResult.status
        render queryResult.value
    }

    def getAsks() {
        Map queryResult = queryService.getAsks('XBTUSD')

        response.status = queryResult.status
        render queryResult.value
    }
}
