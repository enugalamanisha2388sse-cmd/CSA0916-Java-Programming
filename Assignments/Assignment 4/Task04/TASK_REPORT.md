# Task 4 – Event-Handling Forensics

The handler may not run if the listener is not registered or the method signature does not exactly match the listener interface. A misspelled method becomes an overload instead of an override. The label may remain unchanged if its text is never updated or if the wrong component instance is modified.

The repaired program stores each click as a Point, updates the counter, calls repaint(), and draws all stored points inside paintComponent(). Painting directly only inside the click handler is incorrect because minimising/restoring or covering the window causes repainting and erases pixels that were not retained as application state.

`@Override` asks the compiler to verify that a method overrides a superclass/interface method, catching spelling and signature errors.

## Point list vs BufferedImage
A List<Point> stores O(n) point objects and redraws O(n) dots per repaint; it is easy to clear, inspect and serialize. A BufferedImage stores a raster canvas (space proportional to width × height × pixels), making repaint fast by drawing one image, but editing individual strokes is harder unless stroke history is also retained.

## Evidence
Click several locations and capture a screenshot. Minimise and restore the window; capture a second screenshot showing that all dots remain. Add both screenshots to this folder.
