Работаем с тремя проектами:

1. Unity проект
2. Android Library проект, который генерирует Юнити
3. Собственно проект андроид приложения, куда библиотека интегрируется

ВАЖНО: В процессе создания андроид билда Юнити может перезаписать важные файлы проекта:

unityLibrary/src/main/AndroidManifest.xml
unityLibrary/build.gradle
unityLibrary/proguard-unity.txt

Что может сломать проект. Если у вас Юнити и этот андроид проект лежат в одном репо, нужно ревертить изменения в данных файлах.
Вообще всё что генерирует Юнити - в гитигноре, внутри именно проекта библиотеки коммитить нужно только ваш код если вы его вносите.
Например, новые методы активити либо реализации каких-то новых протоколов взаимодействия с юнити плеером (например команды открытия других сцен, навигация и прочее)

Рекомендую после изменений в юнити делать коммит и только после этого собирать андроид библиотеку, чтобы не запутаться и отменить изменения в трёх файлах выше, если они есть.

Можно проект библиотеки сохранить внутри проекта Юнити, в этом случае Юнити не будет каждый раз запрашивать путь куда сохранять проект, но проще запутаться.

Проект андроид библиотеки можно для удобства модификации открыть в андроид студии, в этом случае придётся переключить андроид СДК и юнити не сможет билдить демо приложение, будет выдавать ошибки билда.
Можно не обращать на это внимание и билдить в андроид студии, то что нужно - юнити скомпилирует и обновит.

При выборе архитектур, если ваше устройство работает на ARMv8 и вы работаете на маке с М процессором, можете оставить только arm64 архитектуру. Но если нужно, можете добавить х86 или арм7

AAR библиотеку лучше всего постить в ваш корпоративный мавен чтобы не тянуть файл на несколько мегабайт (или даже гигабайт если много ассетов) в репозиторий.
Здесь для наглядности он добавлен в репозиторий. Можно добавить в гитигнор и добавлять руками. Пустой проект с арм64 архитектурой компилируется в 55 мегабайт aar файл. Добавление оставшихся архитектур увеличит размер до 220 мб.

Общий флоу:
IMPORTANT:

1. Change:
--keep interface com.unity3d.player.IUnityPlayerLifecycleEvents { *; }
to
+-keep interface com.unity3d.player.* { *; }
in
unityLibrary/proguard-unity.txt

2. Remove launcher intent filter in:
unityLibrary/src/main/AndroidManifest.xml

3. Add
implementation 'androidx.activity:activity:1.9.1'
to
unityLibrary/build.gradle

4. Add abstract activity
unityLibrary/src/main/java/com/unity3d/player/GameViewActivity.java

5. Call
gradle :unityLibrary:assembleRelease

to assemble AAR

6. Find AAR at:
unityLibrary/build/outputs/aar/unityLibrary-release.aar

8. Optional: implement changes in demo app (gradle, manifest, activity)

9. IMPORTANT: after rebuild Unity can overwrite gradle or manifest files, track it and REVERT THOSE CHAGES, commit only your code which you write in Android Studio or VS Code yourself
