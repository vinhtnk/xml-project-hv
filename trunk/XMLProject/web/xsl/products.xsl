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

                <div>
                    <xsl:attribute name="id">itemNo-
                        <xsl:value-of select="productID"/>
                    </xsl:attribute>

                    <img class="imgItem">
                        <xsl:attribute name="src">./Image/<xsl:value-of select="img_link" />
                            
                        </xsl:attribute>
                        <xsl:attribute name="title">
                            <xsl:value-of select="productName"/>
                        </xsl:attribute>
                        <xsl:attribute name="onclick">jsUtils.showPopUp('itemNo-
                            <xsl:value-of select="productID"/>')
                        </xsl:attribute>
                    </img>
                    <div>
                        <xsl:attribute name="class">itemText</xsl:attribute>
                        <div>
                            <xsl:attribute name="class">productName</xsl:attribute>
                            <xsl:value-of select="productName" />
                        </div>
                        <div>
                            <xsl:attribute name="class">productPrice</xsl:attribute>VND
                            <xsl:value-of select="price" />
                        </div>
                        
                        <div class="GameDesc onHide">
                            <xsl:value-of select="description" />
                        </div>
                    </div>
                </div>


                <div>
                    <a>
                        <xsl:attribute name="class">btnAddCart</xsl:attribute>
                        <xsl:attribute name="href">#</xsl:attribute>
                        <xsl:attribute name="onclick">jsUtils.addToCart("itemNo-
                            <xsl:value-of select='productID'/>");return false;
                        </xsl:attribute>
                        <img>
                            <xsl:attribute name="src">./Image/cart1.png</xsl:attribute>
                        </img>
                    </a>
                </div>
            </div>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
