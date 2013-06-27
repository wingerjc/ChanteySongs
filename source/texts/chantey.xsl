<?xml version="1.0" ?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="chantey">
    <html>
    <body>
    <xsl:apply-templates select="title"/>
    <b>ID: </b>
    <xsl:apply-templates select="ID"/><br />
    <xsl:apply-templates select="collection"/>
    <xsl:apply-templates select="author"/>
    <xsl:apply-templates select="performer"/>
    <xsl:apply-templates select="use"/>
    <xsl:apply-templates select="crossref"/><br />
    <xsl:apply-templates select="text"/>
    <xsl:apply-templates select="abc"/>
    </body>
    </html>
</xsl:template>

<xsl:template match="title">
    <h2>
    <xsl:value-of select="."/>
    </h2><br />
</xsl:template>

<xsl:template match="collection">
    <b>Collection: </b>
    <a href="../collections/{.}.xml">
    <xsl:value-of select="."/>
    </a><br />
</xsl:template>

<xsl:template match="ID">
    <a href="../indices/{.}.html">
    <xsl:value-of select="."/>
    </a>
</xsl:template>

<xsl:template match="author">
    <b>Composer: </b>
    <xsl:for-each select="personid">
        <xsl:apply-templates select="." />
        <xsl:if test="position() &lt; last()">,
        </xsl:if>
    </xsl:for-each>
    <br />
</xsl:template>

<xsl:template match="performer">
    <b>Performer: </b>
    <xsl:for-each select="personid">
        <xsl:apply-templates select="." />
        <xsl:if test="position() &lt; last()">,
        </xsl:if>
    </xsl:for-each>
</xsl:template>

<xsl:template match="personid">
    <a href="../people/{.}.xml">
    <xsl:value-of select="."/>
    </a>
</xsl:template>

<xsl:template match="use">
    <b>Used For: </b>
    <xsl:for-each select="usetype">
        <xsl:apply-templates select="." />
        <xsl:if test="position() &lt; last()">,
        </xsl:if>
    </xsl:for-each>
    <br />
</xsl:template>

<xsl:template match="usetype">
    <a href="../indices/{.}.html">
    <xsl:value-of select="."/>
    </a>
</xsl:template>

<xsl:template match="crossref">
    <b>See Also: </b>
    <xsl:for-each select="ID">
        <xsl:apply-templates select="."/>
        <xsl:if test="position() &lt; last()">,
        </xsl:if>
    </xsl:for-each>
    <br />
</xsl:template>

<xsl:template match="text">
    <b>Lyrics:</b>
    <pre style="padding-left: 50px;"><xsl:value-of select="."/></pre>
</xsl:template>

<xsl:template match="abc">
    <b>ABC Notation:</b>
    <pre style="padding-left: 50px;"><xsl:value-of select="."/></pre>
</xsl:template>
</xsl:stylesheet>
<!--
<text>
</text>

<abc>
X:
T:
C:
M: 4/4
L: 1/8
K:
|]
</abc>

</chantey>
-->

