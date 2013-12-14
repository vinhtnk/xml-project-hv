<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    <xsl:template match="/" >
        <xsl:apply-templates select="products"/>
    </xsl:template>

    <xsl:template match="products">
        <xsl:for-each select="product[new_product='true']">
            <div class="main-item">
<!--                <table class="tableItem">
                    <tr>
                        <td>-->
                        <ol >
                            <li>
                            <table class="tableItem">
                                <tr >
                                    <td rowspan="5" width="200px">
                                        <div>
                                            <xsl:attribute name="id">itemNo-<xsl:value-of select="productID"/>
                                            </xsl:attribute>

                                            <img class="imgItem">
                                                <xsl:attribute name="src">./Image/<xsl:value-of select="img_link" /></xsl:attribute>
                                                <xsl:attribute name="title"><xsl:value-of select="productName"/></xsl:attribute>
                                                
                                                <xsl:attribute name="onclick">jsUtils.showPopUp('itemNo-<xsl:value-of select="productID"/>')</xsl:attribute>
                                            </img>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                   
                                    <td>
                                        <div>
                                            <xsl:attribute name="class">itemText</xsl:attribute>
                                            <div> 
                                                <xsl:attribute name="class">productName</xsl:attribute>
                                                <xsl:value-of select="productName" />
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div>
                                            <xsl:attribute name="class">price</xsl:attribute>
                                            <xsl:apply-templates select="price" />
<!--                            <xsl:value-of select="price" /> VND-->
                                        </div>
                                    </td>
                                </tr>



                                <tr>
                                    <td>
                                        <div>
                                            <a>
                                                <xsl:attribute name="class">btnAddCart</xsl:attribute>
                                                <xsl:attribute name="href">#</xsl:attribute>
                                                <xsl:attribute name="onclick">addToCartSes('<xsl:value-of select='productID'/>', '<xsl:value-of select='productName'/>', '<xsl:value-of select='price'/>')
                                                </xsl:attribute>
                                                <img>
                                                    <xsl:attribute name="src">./Image/btn_buy.gif</xsl:attribute>
                                                    <xsl:attribute name="class">imgCart</xsl:attribute>
                                                </img>
                                                
                                            </a>
                                        </div>
                                    </td>
                                </tr>
                            </table>
<!--                        </td>
                    </tr>


                </table>-->
                <div class="ProductDesc onHide">
                    <xsl:value-of select="description" />
                </div>

                            </li>
                            </ol>
            </div>
        </xsl:for-each>
    </xsl:template>

    <xsl:template match="price">
        <xsl:value-of select="format-number(., '###,###')" /> VND
    </xsl:template>

</xsl:stylesheet>
