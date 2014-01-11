<?xml version="1.0"?>

<xsl:stylesheet version="2.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="text" />
<xsl:template match="Person">

insert into Person
(
  FirstName,
  MiddleName,
  LastName,
  Title,
  Suffix,
  Born,
  Died,
  ID
)
Values
(
  '<xsl:value-of select="firstName" />',

  <xsl:if test="middleName">'<xsl:value-of select="middleName" />',</xsl:if>
  <xsl:if test="not(middleName)">null,</xsl:if>

  '<xsl:value-of select="lastName" />',

  <xsl:if test="title">'<xsl:value-of select="title" />',</xsl:if>
  <xsl:if test="not(title)">null,</xsl:if>

  <xsl:if test="suffix">'<xsl:value-of select="suffix" />',</xsl:if>
  <xsl:if test="not(suffix)">null,</xsl:if>

  <xsl:if test="born">'<xsl:value-of select="born" />',</xsl:if>
  <xsl:if test="not(born)">null,</xsl:if>

  <xsl:if test="died">'<xsl:value-of select="died" />',</xsl:if>
  <xsl:if test="not(died)">null,</xsl:if>

  '<xsl:value-of select="ID" />'
);
</xsl:template>

</xsl:stylesheet>