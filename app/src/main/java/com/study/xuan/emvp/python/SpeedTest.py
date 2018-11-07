import os

def createViewHolder(content,fileName):
	path = '/Users/xuan/Projects/EMvp/app/src/main/java/com/study/xuan/emvp/python'

	if not os.path.exists(path):
		os.makedirs(path)

	name = fileName + '.java'
	file = open(name,'w');
	file.write(content)

	file.close()

	print ('ok')


contentCode = "package com.study.xuan.emvp.python;\n" \
"import android.content.Context;\n" \
"import android.view.View;\n" \
"import android.widget.TextView;\n" \
"import com.xuan.annotation.ComponentType;\n" \
"import com.xuan.eapi.component.Component;\n" \
"import com.study.xuan.emvp.model.Text;\n" \
"@ComponentType(\n" \
"        value = %s,\n" \
"        view = TextView.class,\n" \
"        attach = Text.class" \
")\n" \
"public class PyThonVH%s extends Component {\n" \
"    public PyThonVH%s(Context context, View itemView) {\n" \
"        super(context, itemView);\n" \
"    }\n" \
"    @Override\n" \
"    public void onBind(int pos, Object item) {\n" \
"    }" \
"}"

fileName = 'PyThonVH%s'


for i in range(1,2000):
	createViewHolder(contentCode%((100+i),i,i),fileName%i)
