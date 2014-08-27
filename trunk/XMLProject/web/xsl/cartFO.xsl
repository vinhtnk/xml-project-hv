<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8"/>

    <!-- TODO customize transformation rules 
         syntax recommendation http://www.w3.org/TR/xslt 
    -->
    
    <xsl:template match="/" >
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="x" page-height="8.5in"
                page-width="11in" margin-top="0.5in" margin-bottom="0.5in"
                margin-left="1in" margin-right="1in">
                    <fo:region-body margin-top="0.5in"/>
                    <fo:region-before extent="1in"/>
                    <fo:region-after extent="0.75in"/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="x">
                <fo:static-content flow-name="xsl-region-before">
                    <fo:block font-size="24pt" font-family="Arial" color="white"
                                line-height= "24pt" background-color="#DB4444"
                                space-after.optimum="15pt" text-align="center" padding-top="3pt">
                    Hóa đơn
                    </fo:block>
                </fo:static-content>
                <fo:static-content flow-name="xsl-region-after">
                    <fo:block font-size="18pt" font-family="monospace"
                                line-height= "24pt" space-after.optimum="15pt"
                                text-align="center" padding-top="3pt">
                    </fo:block>
                </fo:static-content>
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:table border-collapse="separate" table-layout="fixed">
                            <fo:table-column column-width="2cm"/>
                            <fo:table-column column-width="3cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="3cm"/>
                            <fo:table-column column-width="5cm"/>
                            <fo:table-column column-width="5cm"/>

                            <fo:table-body>
                                <fo:table-row>
                                    <fo:table-cell background-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center"> No.</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center"> Mã sản phẩm</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center"> Tên sản phẩm</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center"> Số lượng</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center"> Đơn giá</fo:block>
                                    </fo:table-cell>
                                    <fo:table-cell background-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                        <fo:block text-align="center"> Tổng cộng</fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                                <xsl:for-each select="orderDetails/orderDetail">
                                    <fo:table-row>
                                        <fo:table-cell border-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:number level="single" count="orderDetail"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="productID"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="productName"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="quantity"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">
                                                <xsl:value-of select="format-number(price, '###,###')"/>
                                            </fo:block>
                                        </fo:table-cell>
                                        <fo:table-cell border-color="#9acd32"
                                        border-width="0.5pt" border-style="solid">
                                            <fo:block text-align="center">                                                
                                            <xsl:value-of select="format-number(price * quantity, '###,###')"/>
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
