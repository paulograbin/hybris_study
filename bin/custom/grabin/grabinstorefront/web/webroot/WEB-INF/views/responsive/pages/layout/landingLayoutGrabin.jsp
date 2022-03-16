<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


    aaaa

<template:page pageTitle="${pageTitle}">

    bbbb

    <div id="section1" class="container">
        <cms:pageSlot position="Section1" var="feature" class="Section1">
            <cms:component component="${feature}"/>
        </cms:pageSlot>
    </div>

    ccc

    <div class="row no-margin">
        <div class="col-xs-12 col-md-6 no-space">
            <cms:pageSlot position="Section2A" var="feature" element="div" class="Section2A row no-margin">
                <cms:component component="${feature}" element="div" class="col-xs-12 col-sm-6 no-space yComponentWrapper"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12 col-md-6 no-space">
            <cms:pageSlot position="Section2B" var="feature" element="div" class="Section2B row no-margin">
                <cms:component component="${feature}" element="div" class="col-xs-12 col-sm-6 no-space yComponentWrapper"/>
            </cms:pageSlot>
        </div>
        <div class="col-xs-12">
            <cms:pageSlot position="Section2C" var="feature" element="div" class="Section2C landingLayout2PageSection2C">
                <cms:component component="${feature}" element="div" class="yComponentWrapper"/>
            </cms:pageSlot>
        </div>
    </div>


    dd

    <cms:pageSlot position="Section3" var="feature" element="div" class="row no-margin Section3" >
        <cms:component component="${feature}" element="div" class="no-space yComponentWrapper"/>
    </cms:pageSlot>


    ee

    <cms:pageSlot position="Section4" var="feature" element="div" class="row no-margin Section4">
        <cms:component component="${feature}" element="div" class="col-xs-6 col-md-3 no-space yComponentWrapper"/>
    </cms:pageSlot>


    ffff

    <cms:pageSlot position="Section5" var="feature" element="div Section5">
        <cms:component component="${feature}" element="div" class="yComponentWrapper"/>
    </cms:pageSlot>

    gggg

</template:page>


hhh
