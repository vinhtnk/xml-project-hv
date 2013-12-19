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
                        <input type="text" name="proID" disabled='true'>
                            <xsl:attribute name="value">
                                <xsl:value-of select="productID"/>
                            </xsl:attribute>
                        </input>
                    </td>
                    <td>
                        <input type="text" name="proName">
                            <xsl:attribute name="value">
                                <xsl:value-of select="productName"/>
                            </xsl:attribute>
                        </input>
                    </td>
                    <td>
                        <input type="text" name="cateID">
                            <xsl:attribute name="value">
                                <xsl:value-of select="categoryID"/>
                            </xsl:attribute>
                        </input>
                    </td>
                    <td>
                        <input type="text" name="price">
                            <xsl:attribute name="value">
                                <xsl:value-of select="price"/>
                            </xsl:attribute>
                        </input>
                    </td>
                    <td>
                        <input type="text" name="des">
                            <xsl:attribute name="value">
                                <xsl:value-of select="description"/>
                            </xsl:attribute>
                        </input>
                    </td>
                    <td>
                        <input type="text" name="img">
                            <xsl:attribute name="value">
                                <xsl:value-of select="img_link"/>
                            </xsl:attribute>
                        </input>
                    </td>
                    <td>
                        <input type="text" name="newpro">
                            <xsl:attribute name="value">
                                <xsl:value-of select="new_product"/>
                            </xsl:attribute>
                        </input>
                    </td>
                    <td>
                        <input type="submit" value="Update">
                            <xsl:attribute name="onclick">abcd('<xsl:value-of select="productID"/>')</xsl:attribute>
                        </input>
                    </td>
                    <td>
                        <input type="submit" value="Delete">
                            <xsl:attribute name="id">updateProduct('<xsl:value-of select="productID"/>'/)</xsl:attribute>
                            <xsl:attribute name="onclick">deleteProduct('<xsl:value-of select="productID"/>');return false;</xsl:attribute>
                        </input>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>
