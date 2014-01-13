<?xml version="1.0"?>

<xsl:stylesheet version="2.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="text" />
<xsl:template match="Collection">

insert into SongCollection
(
    Title,
    MediaType,
    Publisher,
    PublisherLocation,
    Published,
    Edition,
    CollectionLength,
    URL,
    ID
)
values
(
    '<xsl:value-of select="title" />',
    '<xsl:value-of select="type" />',

    <xsl:if test="publisher">'<xsl:value-of select="publisher" />'</xsl:if>
    <xsl:if test="not(publisher)">null</xsl:if>,

    <xsl:if test="publisherLocation">'<xsl:value-of select="publisherLocation" />'</xsl:if>
    <xsl:if test="not(publisherLocation)">null</xsl:if>,

    <xsl:if test="published">'<xsl:value-of select="published" />'</xsl:if>
    <xsl:if test="not(published)">null</xsl:if>,

    <xsl:if test="edition"><xsl:value-of select="edition" /></xsl:if>
    <xsl:if test="not(edition)">null</xsl:if>,

    <xsl:value-of select="length" />,

    <xsl:if test="url">'<xsl:value-of select="url" />'</xsl:if>
    <xsl:if test="not(url)">null</xsl:if>,

    '<xsl:value-of select="ID" />'

);

<xsl:for-each select="authors/string">
insert into SongCollectionAuthor
( CollectionID, AuthorID)
values
('<xsl:value-of select="../../ID" />','<xsl:value-of select="." />');

</xsl:for-each>

<xsl:for-each select="editors/string">
insert into SongCollectionEditor
( CollectionID, EditorID)
values
('<xsl:value-of select="../../ID" />','<xsl:value-of select="." />');

</xsl:for-each>

</xsl:template>


</xsl:stylesheet>