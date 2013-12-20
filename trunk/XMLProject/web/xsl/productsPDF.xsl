<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : productPDF.xsl
    Created on : December 19, 2013, 4:22 AM
    Author     : Stork
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/" >
        <xsl:apply-templates select="products"/>
    </xsl:template>
    <xsl:template match="products">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="x" page-height="8.5in" page-width="11in"
								margin-top="0.5in" margin-bottom="0.5in" margin-left="1in" margin-right="1in">
                    <fo:region-before extent="1in" />
                    <fo:region-body margin-top="0.5in" margin-bottom="0.9in"/>
                    <fo:region-after extent="0.35in" />
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="x">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="14pt" font-family="Arial" line-height="24pt" background-color="cyan"
						space-after.optimum="15pt" text-align="center" padding-top="3pt">
				Report detail
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:block padding="0.3in" margin-left="0.5in">
                            <fo:block font-size="13pt" >
                              List Products
                            </fo:block>
                            <fo:table border-collapse="separate" table-layout="fixed">
                                <fo:table-column column-width="3cm"/>
                                <fo:table-column column-width="5cm"/>
                                <fo:table-column column-width="5cm"/>
                                <fo:table-column column-width="5cm"/>
                                <fo:table-column column-width="5cm"/>
                                <fo:table-column column-width="5cm"/>
                                <fo:table-column column-width="5cm"/>
                                <fo:table-column column-width="3cm"/>
                                <fo:table-body>
                                    <fo:table-row>
                                        <fo:table-cell>
                                            <fo:block font-size="13pt" >
                                             ProductID
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="13pt">
                                                Product Name
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="13pt">
                                                Category ID
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="13pt">
                                                Price
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="13pt">
                                                Description
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="13pt">
                                                Image Link
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell>
                                            <fo:block font-size="13pt">
                                                New Product
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                    <xsl:for-each select="product">
                                        <fo:table-row>
                                            <fo:table-cell>
                                                <fo:block  font-size="8pt">
                                                    <xsl:value-of select="productID"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block  font-size="8pt">
                                                    <xsl:value-of select="productName"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block  font-size="8pt">
                                                    <xsl:value-of select="categoryID"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block  font-size="8pt">
                                                    <xsl:value-of select="price"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block  font-size="8pt">
                                                    <xsl:value-of select="description"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block  font-size="8pt">
                                                    <xsl:value-of select="img_link"/>
                                                </fo:block>
                                            </fo:table-cell>
                                            <fo:table-cell>
                                                <fo:block  font-size="8pt">
                                                    <xsl:value-of select="new_product"/>
                                                </fo:block>
                                            </fo:table-cell>
                                        </fo:table-row>
                                    </xsl:for-each>
                                </fo:table-body>
                            </fo:table>
                        </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
