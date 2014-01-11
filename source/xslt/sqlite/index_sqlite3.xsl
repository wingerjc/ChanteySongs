<?xml version="1.0"?>

<xsl:stylesheet version="2.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="text" />
<xsl:template match="Index">

insert into SongIndex
    (ID)
values
    ('<xsl:value-of select="ID" />');

<xsl:for-each select="seeAlso/string">
insert into SongSeeAlso
    (LowerIndex,UpperIndex)
values
    ('<xsl:value-of select="../../ID" />','<xsl:value-of select="." />');
</xsl:for-each>

<xsl:for-each select="crossRef/string">
insert into SongCrossRef
    (LowerIndex,UpperIndex)
values
    ('<xsl:value-of select="../../ID" />','<xsl:value-of select="." />');
</xsl:for-each>

</xsl:template>


</xsl:stylesheet>