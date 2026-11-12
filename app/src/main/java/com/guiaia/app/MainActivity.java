package com.guiaia.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    static final int FONDO = 0xFF050E24, TARJETA = 0xFF0B1B3F, ACENTO = 0xFF1EA7FF,
            TEXTO = 0xFFFFFFFF, SUAVE = 0xFF9FB4D8, BORDE = 0x663A6BB5;
    static final String TODAS = "Todas", COMPARATIVA = "Comparativa", PERFILES = "Perfiles";
    static final String[] PLANES = {"Todos", "Gratis", "Freemium", "De pago", "Incluido"};

    final List<String[]> tools = new ArrayList<>();
    String cat = TODAS, plan = "Todos", query = "";
    LinearLayout filaCat, filaPlan, lista;
    TextView descripcion, contador;
    ScrollView sv;
    EditText buscar;

    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setStatusBarColor(FONDO);
        getWindow().setNavigationBarColor(FONDO);
        cargar();
        mostrarBienvenida();
    }

    // ---------- datos ----------
    void cargar() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("herramientas.txt"), "UTF-8"));
            String l;
            while ((l = br.readLine()) != null) {
                if (l.trim().isEmpty()) continue;
                String[] f = l.split("\\|", -1);
                if (f.length >= 8) tools.add(f);
            }
            br.close();
        } catch (Exception e) { /* sin datos */ }
    }

    static String norm(String s) {
        return Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", "").toLowerCase();
    }

    boolean coincide(String[] t) {
        if (!cat.equals(TODAS) && !cat.equals(t[0])) return false;
        String tipo = t[2];
        switch (plan) {
            case "Gratis": if (!tipo.startsWith("GRATIS")) return false; break;
            case "Freemium": if (!tipo.equals("FREEMIUM")) return false; break;
            case "De pago": if (!tipo.equals("DE PAGO")) return false; break;
            case "Incluido": if (!tipo.startsWith("INCLUIDO")) return false; break;
            default: break;
        }
        if (!query.isEmpty()) {
            String todo = norm(t[1] + " " + t[0] + " " + t[3] + " " + t[4] + " " + t[5]);
            if (!todo.contains(query)) return false;
        }
        return true;
    }

    // ---------- helpers de UI ----------
    int dp(int v) { return Math.round(v * getResources().getDisplayMetrics().density); }

    GradientDrawable caja(int color, int radio, int borde) {
        GradientDrawable g = new GradientDrawable();
        g.setColor(color); g.setCornerRadius(dp(radio));
        if (borde != 0) g.setStroke(dp(1), borde);
        return g;
    }

    TextView texto(String s, int sp, int color, boolean negrita) {
        TextView t = new TextView(this);
        t.setText(s); t.setTextSize(sp); t.setTextColor(color);
        if (negrita) t.setTypeface(Typeface.DEFAULT_BOLD);
        return t;
    }

    int colorPerfil(String p) {
        switch (p) {
            case "verde": return 0xFF22C55E;
            case "azul": return 0xFF0EA5E9;
            case "naranja": return 0xFFF97316;
            case "dorado": return 0xFFEAB308;
            default: return 0xFFFFFFFF; // negro del documento = blanco en tema oscuro
        }
    }

    int colorPlan(String tipo) {
        if (tipo.startsWith("GRATIS")) return 0xFF22C55E;
        if (tipo.equals("FREEMIUM")) return 0xFFEAB308;
        if (tipo.equals("DE PAGO")) return 0xFFEF4444;
        return 0xFF3B82F6;
    }

    TextView chip(String label, boolean sel, View.OnClickListener l) {
        TextView c = texto(label, 14, sel ? Color.WHITE : SUAVE, sel);
        c.setPadding(dp(14), dp(8), dp(14), dp(8));
        c.setBackground(caja(sel ? ACENTO : TARJETA, 20, sel ? ACENTO : BORDE));
        c.setOnClickListener(l);
        return c;
    }

    LinearLayout tarjeta() {
        LinearLayout card = new LinearLayout(this);
        card.setOrientation(LinearLayout.VERTICAL);
        card.setPadding(dp(16), dp(14), dp(16), dp(14));
        card.setBackground(caja(TARJETA, 16, BORDE));
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2);
        lp.bottomMargin = dp(12);
        card.setLayoutParams(lp);
        return card;
    }

    // ---------- pantallas ----------
    void mostrarBienvenida() {
        LinearLayout r = new LinearLayout(this);
        r.setOrientation(LinearLayout.VERTICAL);
        r.setBackgroundColor(FONDO);
        r.setPadding(dp(16), dp(32), dp(16), dp(24));
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.bienvenida_libro);
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        r.addView(img, new LinearLayout.LayoutParams(-1, 0, 1f));
        Button btn = new Button(this);
        btn.setText("Entrar a la guía");
        btn.setTextColor(Color.WHITE); btn.setAllCaps(false); btn.setTextSize(17);
        btn.setBackground(caja(ACENTO, 28, 0));
        btn.setOnClickListener(v -> mostrarPrincipal());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, dp(56));
        lp.topMargin = dp(16);
        r.addView(btn, lp);
        setContentView(r);
    }

    void mostrarPrincipal() {
        LinearLayout r = new LinearLayout(this);
        r.setOrientation(LinearLayout.VERTICAL);
        r.setBackgroundColor(FONDO);
        r.setPadding(dp(16), dp(36), dp(16), 0);

        r.addView(texto(Contenido.TITULO, 22, TEXTO, true));
        TextView s = texto(Contenido.SUBTITULO, 13, SUAVE, false);
        s.setPadding(0, dp(6), 0, dp(10));
        r.addView(s);

        buscar = new EditText(this);
        buscar.setHint("Buscar IA, función o precio…");
        buscar.setHintTextColor(SUAVE); buscar.setTextColor(TEXTO); buscar.setTextSize(15);
        buscar.setSingleLine(true);
        buscar.setPadding(dp(14), dp(10), dp(14), dp(10));
        buscar.setBackground(caja(TARJETA, 14, BORDE));
        buscar.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence a, int b, int c, int d) {}
            public void onTextChanged(CharSequence a, int b, int c, int d) {}
            public void afterTextChanged(Editable e) { query = norm(e.toString().trim()); pintarLista(); }
        });
        r.addView(buscar, new LinearLayout.LayoutParams(-1, -2));

        HorizontalScrollView h1 = new HorizontalScrollView(this);
        h1.setHorizontalScrollBarEnabled(false);
        filaCat = new LinearLayout(this);
        h1.addView(filaCat);
        LinearLayout.LayoutParams l1 = new LinearLayout.LayoutParams(-1, -2);
        l1.topMargin = dp(10);
        r.addView(h1, l1);

        HorizontalScrollView h2 = new HorizontalScrollView(this);
        h2.setHorizontalScrollBarEnabled(false);
        filaPlan = new LinearLayout(this);
        h2.addView(filaPlan);
        LinearLayout.LayoutParams l2 = new LinearLayout.LayoutParams(-1, -2);
        l2.topMargin = dp(8);
        r.addView(h2, l2);

        descripcion = texto("", 13, SUAVE, false);
        descripcion.setPadding(0, dp(10), 0, 0);
        r.addView(descripcion);
        contador = texto("", 12, ACENTO, true);
        contador.setPadding(0, dp(4), 0, 0);
        r.addView(contador);

        sv = new ScrollView(this);
        lista = new LinearLayout(this);
        lista.setOrientation(LinearLayout.VERTICAL);
        lista.setPadding(0, dp(12), 0, dp(24));
        sv.addView(lista);
        r.addView(sv, new LinearLayout.LayoutParams(-1, 0, 1f));
        setContentView(r);
        pintarChips();
        pintarLista();
    }

    void pintarChips() {
        filaCat.removeAllViews();
        List<String> cats = new ArrayList<>();
        cats.add(TODAS);
        for (String[] c : Contenido.CATEGORIAS) cats.add(c[0]);
        cats.add(COMPARATIVA);
        cats.add(PERFILES);
        for (String c : cats) {
            TextView ch = chip(c, c.equals(cat), v -> { cat = c; pintarChips(); pintarLista(); sv.scrollTo(0, 0); });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
            lp.rightMargin = dp(8);
            filaCat.addView(ch, lp);
        }
        filaPlan.removeAllViews();
        for (String p : PLANES) {
            TextView ch = chip(p, p.equals(plan), v -> { plan = p; pintarChips(); pintarLista(); });
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-2, -2);
            lp.rightMargin = dp(8);
            filaPlan.addView(ch, lp);
        }
        boolean especial = cat.equals(COMPARATIVA) || cat.equals(PERFILES);
        filaPlan.setVisibility(especial ? View.GONE : View.VISIBLE);
        ((View) filaPlan.getParent()).setVisibility(especial ? View.GONE : View.VISIBLE);
        buscar.setVisibility(especial ? View.GONE : View.VISIBLE);
    }

    void pintarLista() {
        lista.removeAllViews();
        if (cat.equals(COMPARATIVA)) { pintarComparativa(); return; }
        if (cat.equals(PERFILES)) { pintarPerfiles(); return; }
        String d = "";
        for (String[] c : Contenido.CATEGORIAS) if (c[0].equals(cat)) d = c[1];
        descripcion.setText(d);
        descripcion.setVisibility(d.isEmpty() ? View.GONE : View.VISIBLE);
        int n = 0;
        for (String[] t : tools) {
            if (!coincide(t)) continue;
            n++;
            lista.addView(tarjetaHerramienta(t));
        }
        contador.setText(n + (n == 1 ? " herramienta" : " herramientas"));
        if (n == 0) {
            TextView v = texto("No hay herramientas con ese filtro.", 14, SUAVE, false);
            v.setPadding(0, dp(16), 0, 0);
            lista.addView(v);
        }
    }

    View tarjetaHerramienta(String[] t) {
        LinearLayout card = tarjeta();
        card.addView(texto(t[0].toUpperCase(), 11, ACENTO, true));
        TextView n = texto(t[1], 19, TEXTO, true);
        n.setPadding(0, dp(2), 0, dp(6));
        card.addView(n);

        int cp = colorPlan(t[2]);
        TextView badge = texto(t[2], 11, cp, true);
        badge.setPadding(dp(10), dp(3), dp(10), dp(3));
        badge.setBackground(caja((cp & 0x00FFFFFF) | 0x33000000, 10, cp));
        LinearLayout.LayoutParams bl = new LinearLayout.LayoutParams(-2, -2);
        card.addView(badge, bl);

        TextView pr = texto(t[3], 13, SUAVE, false);
        pr.setTypeface(null, Typeface.ITALIC);
        pr.setPadding(0, dp(6), 0, dp(6));
        card.addView(pr);

        for (String p : t[4].split(";;")) {
            if (p.trim().isEmpty()) continue;
            TextView b = texto("•  " + p.trim(), 14, 0xFFD6E2FA, false);
            b.setPadding(0, dp(2), 0, dp(2));
            card.addView(b);
        }

        SpannableStringBuilder sb = new SpannableStringBuilder();
        String lab = "Ideal para: ";
        sb.append(lab);
        sb.setSpan(new ForegroundColorSpan(colorPerfil(t[7])), 0, lab.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new StyleSpan(Typeface.BOLD), 0, lab.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.append(t[5]);
        TextView id = texto("", 14, SUAVE, false);
        id.setText(sb);
        id.setPadding(0, dp(8), 0, 0);
        card.addView(id);

        TextView ab = texto("Abrir sitio  ›", 14, ACENTO, true);
        ab.setPadding(0, dp(10), 0, 0);
        card.addView(ab);
        final String url = t[6];
        card.setOnClickListener(v -> {
            try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); }
            catch (Exception e) { Toast.makeText(this, "No se pudo abrir el enlace", Toast.LENGTH_SHORT).show(); }
        });
        return card;
    }

    void pintarComparativa() {
        descripcion.setText("Para cada tarea, la opción de pago más recomendada y su mejor alternativa gratuita.");
        descripcion.setVisibility(View.VISIBLE);
        contador.setText("Tabla comparativa: de pago vs. gratis");
        for (String[] f : Contenido.COMPARATIVA) {
            LinearLayout card = tarjeta();
            card.addView(texto(f[0], 17, TEXTO, true));
            TextView a = texto("De pago:  " + f[1], 14, 0xFFEF4444, false);
            a.setPadding(0, dp(6), 0, 0);
            card.addView(a);
            card.addView(texto("Gratis:  " + f[2], 14, 0xFF22C55E, false));
            lista.addView(card);
        }
    }

    void pintarPerfiles() {
        descripcion.setText("La combinación de herramientas que mejor funciona según quién eres.");
        descripcion.setVisibility(View.VISIBLE);
        contador.setText("Recomendaciones por perfil");
        LinearLayout ley = tarjeta();
        ley.addView(texto("Cómo leer el color de «Ideal para:»", 15, TEXTO, true));
        for (String[] l : Contenido.LEYENDA) {
            TextView t = texto("■  " + l[1], 13, colorPerfil(l[0]), false);
            t.setPadding(0, dp(4), 0, 0);
            ley.addView(t);
        }
        lista.addView(ley);
        for (String[] p : Contenido.PERFILES) {
            LinearLayout card = tarjeta();
            card.addView(texto(p[0], 16, colorPerfil(p[3]), true));
            TextView st = texto(p[1], 15, TEXTO, true);
            st.setPadding(0, dp(6), 0, dp(4));
            card.addView(st);
            card.addView(texto(p[2], 14, SUAVE, false));
            lista.addView(card);
        }
    }
}
