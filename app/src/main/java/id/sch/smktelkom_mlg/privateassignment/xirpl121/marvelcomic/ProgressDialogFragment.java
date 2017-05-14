package id.sch.smktelkom_mlg.privateassignment.xirpl121.marvelcomic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;

/**
 * Menunjukkan proses sinkronisasi Cloudant; Download dan Upload dialog.
 */
public class ProgressDialogFragment extends DialogFragment {

    /**
     * Konstruktor ProgressDialogFragment.
     *
     * @param title Isi pesan pada dialog Alert.
     * @return ProgressDialogFragment yang dibangun.
     */
    public static ProgressDialogFragment newInstance(String title) {
        ProgressDialogFragment frag = new ProgressDialogFragment();
        Bundle args = new Bundle();
        args.putString("message", title);

        frag.setArguments(args);
        return frag;
    }

    // Buat dialog proses dengan sifat dan interaksi yang sesuai; Upload vs Download
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String title = getArguments().getString("message");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()).setTitle(title);

        final View view = getActivity().getLayoutInflater().inflate(R.layout.progress, null);

        // Buat listener onclick untuk tombol cancel agar berhenti melakukan replikasi.

        // Buat keylistener untuk menunjukkan replikasi masih berjalan saat tombol back ditekan.

        // Mengatur listener diatas ke dialog yang sedang dibuat.
        builder.setView(view).setCancelable(false);
        return builder.create();
    }
}