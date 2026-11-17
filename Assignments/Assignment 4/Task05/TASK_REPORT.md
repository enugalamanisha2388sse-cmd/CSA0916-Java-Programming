# Task 5 – Same Screen, Different Event Styles

The converter UI contains amount input, From/To currency selectors, Convert button and result label. Enter in the amount field invokes the same conversion routine. Valid and invalid outcomes are shown in a dialog. Window closing is handled with WindowAdapter.

## Four listener styles
1. Frame implements ActionListener: add `implements ActionListener`, register the frame, and implement actionPerformed; compact for a tiny frame but mixes window and command responsibilities.
2. Named inner class: explicit reusable handler class; more lines but easy to name, test and extend.
3. Anonymous inner class: local handler at registration; concise but repeated implementations can clutter construction.
4. Lambda: shortest for a functional interface; concise and can access enclosing frame fields, but best for small actions.

The submitted runnable frame uses lambda for Convert and includes named/anonymous handler examples as alternatives. To demonstrate all four as separate complete variants, duplicate the screen class and switch the listener registration style. Record the changed line count in your local comparison.

## Method reference / shared Action
A method reference such as `convertButton.addActionListener(this::handleConvert)` is concise when a matching method exists. A shared Swing Action can keep enablement, name, icon and shortcut consistent across multiple controls.

## Rate note
The conversion rates are illustrative constants, not live financial rates. Replace them with a validated rate source if real conversion is required.
