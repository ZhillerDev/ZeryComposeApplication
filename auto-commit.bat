@echo off
chcp 65001
set /p message="请输入提交消息："

:: 执行Git命令
git add .
git commit -m "%message%"
git push

pause