require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Добрый день! Я могу расчитать средний расход топлива для вашей машины или мотоцикла. Назовите модель а также год выпуска транспортного средства
        go!: ./WhatIsYourCar

    state: WhatIsYourCar
        a: У вас машина или мотоцикл?
        script:
         $reactions.buttons([
            {text: "Машина", transition: "/Car"}
            {text: "Мотоцикл", transition: "/MotoCycle"}
            ])~~
        state: Car
            a: Напишите название своей машины с годом выпуска

        state: MotoCycle
            a: Напишите название своего мотоцикла с годом выпуска

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

    state: Match
        event!: match
        a: {{$context.intent.answer}}