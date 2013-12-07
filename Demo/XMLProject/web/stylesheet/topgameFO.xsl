<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : StudentFO.xsl
    Created on : June 11, 2013, 5:18 PM
    Author     : Quaikiet
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0"
xmlns:tns="http://xml.netbeans.org/schema/topgames"
>
    <xsl:output method="xml"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="x" page-height="8.5in"
                page-width="11in" margin-top="0.5in" margin-bottom="0.5in"
                margin-left="1in" margin-right="1in">
                    <fo:region-body margin-top="0.5in"/>
                    <fo:region-before extent = "1in"/>
                    <fo:region-after extent = "0.7in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="x">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="14pt" font-family="sans-serif"
                    line-height="24pt" background-color="cyan"
                    space-after.optimum="15pt" text-align="center"
                    padding-top="3pt">
                            TOP <xsl:value-of select="tns:topgames/@NumBerTopGame"/> HOT GAME
                    </fo:block>
                </fo:static-content>
      
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block font-size="18pt" font-family="sans-serif"
                line-height="24pt" space-after.optimum="15pt"
                text-align="center" padding-top="3pt">
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:table border-collapse="separate" table-layout="fixed">
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell border-color="blue"
                                    border-width="0.5pt"
                                    border-style="solid"
                                >
                                        <fo:block text-align="center">No.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Game Name</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Price</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Quantity</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell border-color="blue"
                                border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center">Total</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each select="tns:topgames/tns:TopGame">
                                    <fo:table-row>
                                        <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:number level="single" count="tns:TopGame"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="tns:Name"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="tns:Price"/>
                                            </fo:block>
                                        </fo:table-cell>
                                         <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="tns:Quantity"/>
                                            </fo:block>
                                        </fo:table-cell>
                                         <fo:table-cell border-color="blue" border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="tns:Total"/>
                                            </fo:block>
                                        </fo:table-cell>
                                    </fo:table-row>
                                </xsl:for-each>
                            </fo:table-body>
                        </fo:table>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>
