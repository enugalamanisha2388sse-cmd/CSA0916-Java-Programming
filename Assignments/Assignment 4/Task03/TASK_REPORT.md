# Task 3 – From Sketch to Layout

## Layout decisions
- JFrame uses BorderLayout: title at NORTH, search controls at PAGE_START, scrollable results at CENTER, actions/status at SOUTH.
- Search row and bottom action row use FlowLayout to preserve natural control sizes.
- Results use JScrollPane so the centre expands when the window grows.
- Nested panels keep each region independent; GridBagLayout is an alternative for more precise form alignment. Absolute positioning is fragile because controls do not adapt to font, platform or window size.

## Nested structure
```
JFrame BorderLayout
  JLabel title (NORTH)
  JPanel FlowLayout search controls (PAGE_START)
  JScrollPane -> JList results (CENTER)
  JPanel BorderLayout (SOUTH)
    JPanel FlowLayout Prev / Next / Reserve (CENTER)
    JLabel status (SOUTH)
```

## Test evidence
Run at 420x300, 700x480 and 1000x700. Confirm the centre results area grows while the search row and bottom buttons remain usable. Capture screenshots at all three sizes and add them to this folder. The program uses sample data; connect a repository for real catalogue search.
