<?xml version="1.0"?>

<xsl:stylesheet version="2.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="text" />
<xsl:template match="Song">

insert into Song
(
    ID,
    Title,
    IndexID,
    CollectionID,
    CollectionLocation,
    CollectionGeographicLocation,
    Lyrics,
    abc

)
values
(
    '<xsl:value-of select="ID" />',
    '<xsl:value-of select="replace(title,'''','''''')" />',
    '<xsl:value-of select="indexID" />',
    '<xsl:value-of select="collection" />',
    <xsl:value-of select="collectionLocation" />,
    <xsl:if test="collectionGeographicLocation">'<xsl:value-of select="collectionGeographicLocation" />'</xsl:if>
    <xsl:if test="not(collectionGeographicLocation)">null</xsl:if>,
    <xsl:if test="lyrics">'<xsl:value-of select="replace(lyrics,'''','''''')" />'</xsl:if>
    <xsl:if test="not(lyrics)">null</xsl:if>,
    <xsl:if test="abc"><xsl:value-of select="replace(abc,'''','''''')" /></xsl:if>
    <xsl:if test="not(abc)">null</xsl:if>
);

<xsl:for-each select="composers/string">
insert into SongComposer
( SongID, PersonID )
values
( '<xsl:value-of select="../../ID"/>', '<xsl:value-of select="." />' );
</xsl:for-each>

<xsl:for-each select="arrangers/string">
insert into SongArranger
( SongID, PersonID )
values
( '<xsl:value-of select="../../ID"/>', '<xsl:value-of select="." />' );
</xsl:for-each>

<xsl:for-each select="lyricist/string">
insert into SongLyricist
( SongID, PersonID )
values
( '<xsl:value-of select="../../ID"/>', '<xsl:value-of select="." />' );
</xsl:for-each>

<xsl:for-each select="performers/string">
insert into SongPerformer
( SongID, PersonID )
values
( '<xsl:value-of select="../../ID"/>', '<xsl:value-of select="." />' );
</xsl:for-each>

<xsl:for-each select="collectors/string">
insert into SongCollector
( SongID, PersonID )
values
( '<xsl:value-of select="../../ID"/>', '<xsl:value-of select="." />' );
</xsl:for-each>

</xsl:template>


</xsl:stylesheet>