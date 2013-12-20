<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : add_products.xsl
    Created on : December 16, 2013, 2:31 PM
    Author     : Stork
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/" >
        <xsl:apply-templates select="products"/>
    </xsl:template>

    <xsl:template match="products">
        
        <table border="1" width="750px" id="tbProduct">
            <tr>
                <td >
                                    Product ID
                </td>
                <td>
                                    Product Name
                </td>
                <td>
                                    Category ID
                </td>
                <td>
                                    Price
                </td>
                <td>
                                    Description
                </td>
                <td>
                                    Image_Link
                </td>
                <td>
                                    New_Product
                </td>
                <td>
                                    Update
                </td>
                <td>
                                    Delete
                </td>
            </tr>
            <xsl:for-each select="product">
                <tr>  
                    <td>
                                <xsl:value-of select="productID"/>
                    </td>
                    <td>
                                <xsl:value-of select="productName"/>
                    </td>
                    <td>
                                <xsl:value-of select="categoryID"/>
                    </td>
                    <td>
                                <xsl:value-of select="price"/>
                    </td>
                    <td>
                                <xsl:value-of select="description"/>
                    </td>
                    <td>
                                <xsl:value-of select="img_link"/>
                    </td>
                    <td>
                                <xsl:value-of select="new_product"/>
                    </td>
                    <td>
                        <a>
                            <xsl:attribute name="href">updateProduct.jsp?Val=<xsl:value-of select="productID"/></xsl:attribute>
                            Update
                        </a>
                    </td>
                    <td>
                        <input type="submit" value="Delete">
                            <xsl:attribute name="id">updateProduct('
                                <xsl:value-of select="productID"/>'/)
                            </xsl:attribute>
                            <xsl:attribute name="onclick">deleteProduct('
                                <xsl:value-of select="productID"/>');return false;
                            </xsl:attribute>
                        </input>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>
