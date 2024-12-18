package FreeMarker template error (DEBUG mode; use RETHROW in production!):
The following has evaluated to null or missing:
==> client_dto_package  [in template "templates/dto.java.ftl" at line 1, column 11]

----
Tip: If the failing expression is known to legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: ${client_dto_package}  [in template "templates/dto.java.ftl" at line 1, column 9]
----

Java stack trace (for programmers):
----
freemarker.core.InvalidReferenceException: [... Exception message was already printed; see it above ...]
	at freemarker.core.InvalidReferenceException.getInstance(InvalidReferenceException.java:134)
	at freemarker.core.EvalUtil.coerceModelToTextualCommon(EvalUtil.java:481)
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:401)
	at freemarker.core.EvalUtil.coerceModelToStringOrMarkup(EvalUtil.java:370)
	at freemarker.core.DollarVariable.calculateInterpolatedStringOrMarkup(DollarVariable.java:100)
	at freemarker.core.DollarVariable.accept(DollarVariable.java:63)
	at freemarker.core.Environment.visit(Environment.java:347)
	at freemarker.core.Environment.visit(Environment.java:353)
	at freemarker.core.Environment.process(Environment.java:326)
	at freemarker.template.Template.process(Template.java:383)
	at com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine.writer(FreemarkerTemplateEngine.java:52)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.outputFile(AbstractTemplateEngine.java:196)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.lambda$outputCustomFile$0(AbstractTemplateEngine.java:83)
	at java.util.ArrayList.forEach(ArrayList.java:1259)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.outputCustomFile(AbstractTemplateEngine.java:76)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.lambda$batchOutput$1(AbstractTemplateEngine.java:247)
	at java.util.Optional.ifPresent(Optional.java:159)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.lambda$batchOutput$2(AbstractTemplateEngine.java:243)
	at java.util.ArrayList.forEach(ArrayList.java:1259)
	at com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine.batchOutput(AbstractTemplateEngine.java:241)
	at com.baomidou.mybatisplus.generator.AutoGenerator.execute(AutoGenerator.java:185)
	at com.baomidou.mybatisplus.generator.FastAutoGenerator.execute(FastAutoGenerator.java:239)
	at com.example.mybatisgen.CodeGenerator.main(CodeGenerator.java:80)
