<#-- @ftlvariable name="version" type="java.lang.String" -->
<#include "skeleton.ftl">

<#macro body>
<div class="container" style="margin-top: 20px">
    <div class="container" style="margin-top: 80px">
        <div class="jumbotron">
            <h2 style="font-size: 50px">Smart VertX</h2>
            <p>Starter Template</p>
            <div>
                <small style="color: darkgray">
                    SmartVertX v.<span id="version">${version}</span>
                </small>
            </div>
            <br/>
            <p><a class="btn btn-primary btn-lg" href="/page" role="button">More...</a></p>
        </div>
    </div>
</div>
</#macro>

<@skeleton/>
